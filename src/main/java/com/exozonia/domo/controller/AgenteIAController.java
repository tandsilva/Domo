package com.exozonia.domo.controller;



import com.exozonia.domo.dto.AgenteIADTO;
import com.exozonia.domo.model.QValor;
import com.exozonia.domo.service.AgenteIAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agente-ia")
public class AgenteIAController {

    @Autowired
    private AgenteIAService agenteIAService;

    @PostMapping("/interagir")
    public void interagir(@RequestBody AgenteIADTO dto) {
        agenteIAService.processarInteracao(dto);
    }
    @GetMapping("/qvalores")
    public List<QValor> listarQValores() {
        return agenteIAService.listarQValores();
    }
    @GetMapping("/resposta-conhecimento")
    public String respostaComConhecimento(@RequestParam String comando) {
        return agenteIAService.responderComConhecimento(comando);
    }

}
