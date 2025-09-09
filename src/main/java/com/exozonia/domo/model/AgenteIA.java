package com.exozonia.domo.model;

import java.util.HashMap;
import java.util.Map;

public class AgenteIA {
    private Map<String, Double> qValores;  // Armazena os valores de Q para perguntas/respostas
    private final double taxaAprendizado = 0.1;
    private final double fatorDesconto = 0.9;
    private final double epsilon = 0.1;  // Probabilidade de exploração (random action)

    public AgenteIA() {
        qValores = new HashMap<>();
    }

    public void interagir(InteracaoIA interacao, Sentimento sentimento) {
        String pergunta = interacao.getComando();
        String respostaIA = interacao.getRespostaDaIA();
        double recompensa = obterRecompensa(sentimento);

        // Atualiza os valores de Q para a pergunta e resposta
        atualizarQValores(pergunta, respostaIA, recompensa);
    }

    private double obterRecompensa(Sentimento sentimento) {
        if ("positivo".equals(sentimento.getTipo())) {
            return 1.0;  // Recompensa positiva
        } else if ("negativo".equals(sentimento.getTipo())) {
            return -1.0;  // Penalização
        }
        return 0.0;  // Recompensa neutra
    }

    private void atualizarQValores(String pergunta, String resposta, double recompensa) {
        String chave = pergunta + ":" + resposta;

        double qValorAtual = qValores.getOrDefault(chave, 0.0);
        double novoQValor = qValorAtual + taxaAprendizado * (recompensa + fatorDesconto * obterMaxQValor(pergunta) - qValorAtual);

        qValores.put(chave, novoQValor);
    }

    private double obterMaxQValor(String pergunta) {
        // Retorna o valor máximo de Q para todas as possíveis respostas a essa pergunta
        return qValores.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(pergunta))
                .mapToDouble(Map.Entry::getValue)
                .max()
                .orElse(0.0);
    }
}
