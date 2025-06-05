package com.ExoZonia.Domo.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String gamerTag;
    private String email;
    private String telefone;

    // A ideia do DTO é enviar apenas os dados necessários para a comunicação
}
