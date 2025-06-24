//package com.exozonia.domo.service;
//
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class BrainyacAutoLearningService {
//
//    private final BrainyacLearningService learningService;
//    private final DataFetcherService dataFetcher;    // Serviço para pegar dados externos
//    private final NlpProcessor nlpProcessor;          // Serviço para processar texto e extrair pergunta/resposta
//
//    public BrainyacAutoLearningService(BrainyacLearningService learningService,
//                                       DataFetcherService dataFetcher,
//                                       NlpProcessor nlpProcessor) {
//        this.learningService = learningService;
//        this.dataFetcher = dataFetcher;
//        this.nlpProcessor = nlpProcessor;
//    }
//
//    public void aprenderSozinho() {
//        // Pega dados novos (ex: textos, artigos, etc)
//        List<String> dadosExternos = dataFetcher.buscarDadosExternos();
//
//        for (String dado : dadosExternos) {
//            // Extrai pergunta e resposta usando NLP
//            String pergunta = nlpProcessor.extrairPergunta(dado);
//            String resposta = nlpProcessor.gerarResposta(dado);
//
//            // Usa seu serviço existente para salvar o conhecimento
//            learningService.ensinar(pergunta, resposta);
//        }
//    }
//}
