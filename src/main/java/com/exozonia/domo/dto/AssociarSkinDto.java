package com.exozonia.domo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociarSkinDto {
    private Long usuarioId;
    private Long skinId;
}
