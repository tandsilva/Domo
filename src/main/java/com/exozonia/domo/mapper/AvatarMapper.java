package com.exozonia.domo.mapper;

import com.exozonia.domo.dto.AvatarDto;
import com.exozonia.domo.model.Avatar;

public class AvatarMapper {

    public static AvatarDto toDto(Avatar avatar) {
        return new AvatarDto(
                avatar.getId(),
                avatar.getPele(),
                avatar.getEspecies(),
                avatar.getStatus(),
                avatar.getNomeVisual()
        );
    }

    public static Avatar toEntity(AvatarDto dto) {
        Avatar avatar = new Avatar();
        avatar.setId(dto.getId());
        avatar.setPele(dto.getPele());
        avatar.setEspecies(dto.getEspecies());
        avatar.setStatus(dto.getStatus());
        avatar.setNomeVisual(dto.getNomeVisual());
        return avatar;
    }
}
