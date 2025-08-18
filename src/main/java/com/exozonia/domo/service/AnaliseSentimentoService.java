
package com.exozonia.domo.service;

import edu.stanford.nlp.pipeline.*;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class AnaliseSentimentoService {

    private final StanfordCoreNLP pipeline;

    public AnaliseSentimentoService() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,parse,sentiment");
        this.pipeline = new StanfordCoreNLP(props);
    }

    public String analisarSentimento(String texto) {
        CoreDocument doc = new CoreDocument(texto);
        pipeline.annotate(doc);
        String sentimentoIngles = doc.sentences().get(0).sentiment();
        String sentimentoPortugues = traduzirSentimento(sentimentoIngles);
        return responderComBaseNoSentimento(sentimentoPortugues);
    }

    private String traduzirSentimento(String sentimentoIngles) {
        return switch (sentimentoIngles.toLowerCase()) {
            case "very positive" -> "muito positivo";
            case "positive" -> "positivo";
            case "neutral" -> "neutro";
            case "negative" -> "negativo";
            case "very negative" -> "muito negativo";
            default -> "indefinido";
        };
    }

    private String responderComBaseNoSentimento(String sentimentoPortugues) {
        return switch (sentimentoPortugues) {
            case "muito negativo", "negativo" ->
                    "Parece que você está passando por um momento difícil. Que tal ouvir uma música relaxante ou conversar com alguém?";
            case "positivo", "muito positivo" ->
                    "Que bom saber disso! Continue assim! 🎉";
            case "neutro" ->
                    "Tudo certo por aí? Se quiser compartilhar mais, estou aqui.";
            default ->
                    "Não consegui entender bem como você está se sentindo. Pode tentar explicar de outro jeito?";
        };
    }
}
