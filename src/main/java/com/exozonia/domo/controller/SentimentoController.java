package com.exozonia.domo.controller;

import com.exozonia.domo.dto.SentimentoRequest;
import com.exozonia.domo.dto.SentimentoResponse;
import com.exozonia.domo.service.AnaliseSentimentoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sentimento")
public class SentimentoController {

    private final AnaliseSentimentoService analiseSentimentoService;

    public SentimentoController(AnaliseSentimentoService analiseSentimentoService) {
        this.analiseSentimentoService = analiseSentimentoService;
    }

    @Operation(summary = "Analisa o sentimento de um texto")
    @PostMapping
    public ResponseEntity<SentimentoResponse> analisar(@RequestBody SentimentoRequest request) {
        String sentimento = analiseSentimentoService.analisarSentimento(request.texto());
        return ResponseEntity.ok(new SentimentoResponse(sentimento));
    }
}
