package com.exozonia.domo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkinDto {
    private Long id;

    private String nome;       // Nome da skin (ex: "Skin do Robô", "Skin de Urso")
    private String cor;        // Cor da skin, ou outro atributo visual
    private Long avatarId;     // <-- Novo campo para associar skin a um avatar
}
