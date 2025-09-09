package com.exozonia.domo.service;

import com.exozonia.domo.dto.AgenteIADTO;
import com.exozonia.domo.mapper.AgenteIAMapper;
import com.exozonia.domo.model.InteracaoIA;
import com.exozonia.domo.model.QValor;
import com.exozonia.domo.model.Sentimento;
import com.exozonia.domo.repository.QValorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * Serviço responsável por processar interações da IA e aplicar aprendizado por reforço.
 * Utiliza Q-Learning para atualizar os valores de recompensa associados a pares pergunta:resposta.
 */
@Service
public class AgenteIAService {

    // Repositório para persistência dos valores de Q no banco Neo4j
    private final QValorRepository qValorRepository;

    // Taxa de aprendizado (alpha): define o quanto o novo valor influencia o valor atual
    private final double taxaAprendizado = 0.1;

    // Fator de desconto (gamma): define o peso do valor futuro na atualização
    private final double fatorDesconto = 0.9;

    @Autowired
    public AgenteIAService(QValorRepository qValorRepository) {
        this.qValorRepository = qValorRepository;
    }

    /**
     * Processa uma interação recebida via DTO, calcula a recompensa e atualiza o valor de Q.
     * @param dto Objeto contendo comando, resposta da IA e tipo de sentimento.
     */
    public void processarInteracao(AgenteIADTO dto) {
        // Converte o DTO para os modelos internos
        InteracaoIA interacao = AgenteIAMapper.toInteracaoIA(dto);
        Sentimento sentimento = AgenteIAMapper.toSentimento(dto);

        // Extrai a pergunta e resposta
        String pergunta = interacao.getComando();
        String resposta = interacao.getRespostaDaIA();
        String chave = pergunta + ":" + resposta;

        // Define a recompensa com base no tipo de sentimento
        double recompensa = switch (sentimento.getTipo()) {
            case "positivo" -> 1.0;
            case "negativo" -> -1.0;
            default -> 0.0;
        };

        // Busca o valor atual de Q para essa chave no banco
        double qValorAtual = qValorRepository.findByChave(chave)
                .map(QValor::getValor)
                .orElse(0.0);

        // Busca o maior valor de Q para todas as respostas possíveis da mesma pergunta
        double maxQ = qValorRepository.findAll().stream()
                .filter(q -> q.getChave().startsWith(pergunta + ":"))
                .mapToDouble(QValor::getValor)
                .max()
                .orElse(0.0);

        // Aplica a fórmula de Q-Learning para atualizar o valor
        double novoQValor = qValorAtual + taxaAprendizado * (recompensa + fatorDesconto * maxQ - qValorAtual);

        // Cria ou atualiza o objeto QValor no banco
        QValor qValor = qValorRepository.findByChave(chave).orElse(new QValor());
        qValor.setChave(chave);
        qValor.setValor(novoQValor);
        qValorRepository.save(qValor);
    }

    /**
     * Lista todos os valores de Q armazenados no banco.
     * @return Lista de QValor com chave e valor.
     */
    public List<QValor> listarQValores() {
        return qValorRepository.findAll();
    }
    public String responderComConhecimento(String comando) {
        List<QValor> respostas = qValorRepository.findAll().stream()
                .filter(q -> q.getChave().startsWith(comando + ":"))
                .sorted(Comparator.comparingDouble(QValor::getValor).reversed())
                .toList();

        if (respostas.isEmpty()) {
            return "Ainda não aprendi a responder isso.";
        }

        QValor melhor = respostas.get(0);
        String resposta = melhor.getChave().split(":", 2)[1];
        double valor = melhor.getValor();

        if (valor >= 0.7) {
            return "Com certeza: " + resposta;
        } else if (valor >= 0.3) {
            return "Acho que a melhor resposta seria: " + resposta;
        } else if (valor >= 0.0) {
            return "Não tenho certeza, mas talvez: " + resposta;
        } else {
            return "Essa resposta não parece ser boa. Prefiro não responder.";
        }
    }

}
