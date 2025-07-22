package com.exozonia.domo.mapper;

import com.exozonia.domo.dto.SkinDto;
import com.exozonia.domo.model.Skin;

public class SkinMapper {

    public static Skin toEntity(SkinDto dto) {
        Skin skin = new Skin();
        skin.setNome(dto.getNome());

        return skin;
    }

    public static SkinDto toDto(Skin skin) {
        SkinDto dto = new SkinDto();
        dto.setId(skin.getId());                // adiciona o id
        dto.setNome(skin.getNome());


        return dto;
    }

}
