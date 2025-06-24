package com.exozonia.domo.mapper;

import com.exozonia.domo.dto.UsuarioDto;
import com.exozonia.domo.model.Usuario;

public class UsuarioMapper {

    public static UsuarioDto toDto(Usuario usuario) {
        UsuarioDto dto = new UsuarioDto();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setGamerTag(usuario.getGamerTag());
        dto.setEmail(usuario.getEmail());
        dto.setTelefone(usuario.getTelefone());
        return dto;
    }

    public static Usuario toEntity(UsuarioDto dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId()); // normalmente omitido no POST
        usuario.setNome(dto.getNome());
        usuario.setGamerTag(dto.getGamerTag());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());
        return usuario;
    }
}
