package com.exozonia.domo.mapper;


import com.exozonia.domo.dto.AgenteIADTO;
import com.exozonia.domo.model.InteracaoIA;
import com.exozonia.domo.model.Sentimento;

import java.time.LocalDateTime;

public class AgenteIAMapper {

    public static InteracaoIA toInteracaoIA(AgenteIADTO dto) {
        InteracaoIA interacao = new InteracaoIA();
        interacao.setComando(dto.getComando());
        interacao.setRespostaDaIA(dto.getRespostaDaIA());
        interacao.setDataHora(LocalDateTime.now());
        return interacao;
    }

    public static Sentimento toSentimento(AgenteIADTO dto) {
        Sentimento sentimento = new Sentimento();
        sentimento.setTipo(dto.getTipoSentimento());
        return sentimento;
    }
}
