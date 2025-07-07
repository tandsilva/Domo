package com.exozonia.domo.dto;

import lombok.Data;

@Data
public class UsuarioUpdateDto {
    private String nome;
    private String gamerTag;
    private String email;
    private String telefone;
    private String endereco;
    private String cidade;
    private String estado;
    private String pais;
}
