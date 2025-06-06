package com.ExoZonia.Domo.controller;

import com.ExoZonia.Domo.service.BrainyacLearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brainyac")
public class BrainyacController {

    @Autowired
    private BrainyacLearningService learningService;

    // Endpoint para fazer perguntas
    @GetMapping("/perguntar")
    public String perguntar(@RequestParam String pergunta) {
        return learningService.consultar(pergunta);
    }

    // Endpoint para ensinar (treinar)
    @PostMapping("/ensinar")
    public String ensinar(@RequestParam String pergunta, @RequestParam String resposta) {
        learningService.ensinar(pergunta, resposta);
        return "Aprendi algo novo!";
    }
}
