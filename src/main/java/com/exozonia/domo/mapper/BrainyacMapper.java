package com.exozonia.domo.mapper;



import com.exozonia.domo.dto.BrainyacDto;
import com.exozonia.domo.model.Conhecimento;

public class BrainyacMapper {

    public static Conhecimento toEntity(BrainyacDto dto) {
        return new Conhecimento(dto.getPergunta(), dto.getResposta());
    }

    public static BrainyacDto toDto(Conhecimento conhecimento) {
        BrainyacDto dto = new BrainyacDto();
        dto.setPergunta(conhecimento.getPergunta());
        dto.setResposta(conhecimento.getResposta());
        return dto;
    }
}
