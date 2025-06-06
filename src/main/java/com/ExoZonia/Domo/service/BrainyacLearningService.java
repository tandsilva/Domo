package com.ExoZonia.Domo.service;

import com.ExoZonia.Domo.I.a.Brainyac;
import com.ExoZonia.Domo.model.Conhecimento;
import com.ExoZonia.Domo.repository.ConhecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BrainyacLearningService {

    private final ConhecimentoRepository conhecimentoRepository;
    private final Brainyac brainyac;

    @Autowired
    public BrainyacLearningService(ConhecimentoRepository conhecimentoRepository, Brainyac brainyac) {
        this.conhecimentoRepository = conhecimentoRepository;
        this.brainyac = brainyac;
    }

    /**
     * Ensina uma nova pergunta e resposta, salvando no banco de dados se ainda não existir.
     */
    @Transactional
    public void ensinar(String pergunta, String resposta) {
        System.out.println("📢 Chamando ensinar()...");
        String key = pergunta.toLowerCase().trim();

        if (conhecimentoRepository.existsById(key)) {
            System.out.println("⚠️ Já existe conhecimento para essa pergunta: " + key);
        } else {
            Conhecimento conhecimento = new Conhecimento(key, resposta);
            conhecimentoRepository.save(conhecimento);
            System.out.println("✅ Aprendido: " + key + " → " + resposta);
        }
    }

    /**
     * Consulta uma resposta no banco de dados com resposta padrão.
     */
    public String consultar(String pergunta, String defaultResposta) {
        return conhecimentoRepository.findById(pergunta.toLowerCase().trim())
                .map(Conhecimento::getResposta)
                .orElse(defaultResposta);
    }

    /**
     * Consulta uma resposta no banco de dados com resposta padrão automática.
     */
    public String consultar(String pergunta) {
        return consultar(pergunta, "Desculpe, ainda não sei responder isso.");
    }
}
