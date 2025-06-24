package com.exozonia.domo.controller;

import com.exozonia.domo.dto.BrainyacDto;
import com.exozonia.domo.mapper.BrainyacMapper;
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
//converte o json em objeto java
    // Endpoint para ensinar (usando DTO + Mapper)
    @PostMapping("/ensinar")
    public String ensinar(@RequestBody BrainyacDto dto) {
        if (dto.getPergunta() == null || dto.getPergunta().trim().isEmpty()
                || dto.getResposta() == null || dto.getResposta().trim().isEmpty()) {
            return "Erro: pergunta e resposta são obrigatórias.";
        }

        Conhecimento conhecimento = BrainyacMapper.toEntity(dto);
        learningService.ensinar(conhecimento.getPergunta(), conhecimento.getResposta());

        return "Aprendi algo novo!";
    }
}
