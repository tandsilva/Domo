package com.exozonia.domo.mapper;



import com.exozonia.domo.dto.SkinDto;
import com.exozonia.domo.model.Avatar;
import com.exozonia.domo.model.Skin;

public class SkinMapper {

    public static Skin toEntity(SkinDto dto, Avatar avatar, String imagemPath) {
        Skin skin = new Skin();
        skin.setNome(dto.getNome());
        skin.setCor(dto.getCor());
        skin.setAvatar(avatar);
        skin.setImagemPath(imagemPath);
        return skin;
    }

    public static SkinDto toDto(Skin skin) {
        SkinDto dto = new SkinDto();
        dto.setNome(skin.getNome());
        dto.setCor(skin.getCor());

        return dto;
    }
}
