package com.exozonia.domo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvatarDto {
    private Long id;
    private String pele;
    private String especies;
    private String status;
    private String nomeVisual;
}
