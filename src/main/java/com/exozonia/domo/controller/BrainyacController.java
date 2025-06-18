package com.exozonia.domo.controller;


import com.exozonia.domo.model.Conhecimento;
import com.exozonia.domo.service.BrainyacLearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brainyac")
public class BrainyacController {

    @Autowired
    private BrainyacLearningService learningService;

    // Endpoint para consultar a resposta a uma pergunta
    @GetMapping("/perguntar")
    public String perguntar(@RequestParam String pergunta) {
        return learningService.consultar(pergunta);
    }

    // Endpoint para ensinar (salvar nova pergunta e resposta)
    // Recebe um objeto Conhecimento no corpo da requisição em JSON
    @PostMapping("/ensinar")
    public String ensinar(@RequestBody Conhecimento conhecimento) {
        if (conhecimento.getPergunta() == null || conhecimento.getPergunta().trim().isEmpty()
                || conhecimento.getResposta() == null || conhecimento.getResposta().trim().isEmpty()) {
            return "Erro: pergunta e resposta são obrigatórias.";
        }
        learningService.ensinar(conhecimento.getPergunta(), conhecimento.getResposta());
        return "Aprendi algo novo!";
    }
}
