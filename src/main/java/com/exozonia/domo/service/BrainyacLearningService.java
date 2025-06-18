package com.exozonia.domo.service;

import com.exozonia.domo.model.Conhecimento;
import com.exozonia.domo.repository.ConhecimentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service  // Marca esta classe como um serviço Spring gerenciado
public class BrainyacLearningService {

    // Logger para registrar informações, avisos e erros durante a execução
    private static final Logger logger = LoggerFactory.getLogger(BrainyacLearningService.class);

    // Repositório para acessar o banco de dados Neo4j dos conhecimentos
    private final ConhecimentoRepository conhecimentoRepository;

    // Construtor para injeção de dependência do repositório
    public BrainyacLearningService(ConhecimentoRepository conhecimentoRepository) {
        this.conhecimentoRepository = conhecimentoRepository;
    }

    /**
     * Ensina uma nova pergunta e resposta, salvando no banco se ainda não existir.
     * Se a pergunta já existir, não substitui e apenas loga.
     *
     * @param pergunta a pergunta que será aprendida (chave)
     * @param resposta a resposta associada à pergunta
     */
    @Transactional  // Executa dentro de uma transação para consistência no banco
    public void ensinar(String pergunta, String resposta) {
        logger.info("Chamando ensinar()...");

        // Valida se a pergunta é válida
        if (pergunta == null || pergunta.trim().isEmpty()) {
            logger.warn("Pergunta inválida.");
            return;
        }

        String key = pergunta.toLowerCase().trim();

        // Verifica se o conhecimento já existe no banco pelo ID (pergunta)
        if (conhecimentoRepository.existsById(key)) {
            logger.warn("Já existe conhecimento para essa pergunta: {}", key);
        } else {
            Conhecimento conhecimento = new Conhecimento(key, resposta);
            conhecimentoRepository.save(conhecimento);
            logger.info("Aprendido: {} → {}", key, resposta);
        }
    }

    /**
     * Consulta uma resposta no banco de dados para uma pergunta, usando Optional para evitar null.
     * Retorna um Optional para que o chamador decida o que fazer se não encontrar resposta.
     *
     * @param pergunta a pergunta a ser consultada
     * @return Optional contendo a resposta, ou vazio se não encontrado
     */
    public Optional<String> consultarOptional(String pergunta) {
        if (pergunta == null || pergunta.trim().isEmpty()) {
            return Optional.empty();
        }

        String key = pergunta.toLowerCase().trim();

        return conhecimentoRepository.findById(key)
                .map(Conhecimento::getResposta);
    }

    /**
     * Consulta uma resposta no banco de dados com resposta padrão caso não encontre.
     *
     * @param pergunta a pergunta a ser consultada
     * @param defaultResposta resposta padrão caso a pergunta não seja encontrada
     * @return a resposta encontrada ou a resposta padrão
     */
    public String consultar(String pergunta, String defaultResposta) {
        return consultarOptional(pergunta).orElse(defaultResposta);
    }

    /**
     * Consulta uma resposta no banco de dados com resposta padrão automática.
     *
     * @param pergunta a pergunta a ser consultada
     * @return a resposta encontrada ou uma resposta padrão genérica
     */
    public String consultar(String pergunta) {
        return consultar(pergunta, "Desculpe, ainda não sei responder isso.");
    }
}
