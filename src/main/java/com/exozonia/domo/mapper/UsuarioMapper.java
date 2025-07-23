package com.exozonia.domo.mapper;

import com.exozonia.domo.dto.UsuarioDto;
import com.exozonia.domo.dto.UsuarioUpdateDto;
import com.exozonia.domo.model.Usuario;

public class UsuarioMapper {

    public static UsuarioDto toDto(Usuario usuario) {
        UsuarioDto dto = new UsuarioDto();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setGamerTag(usuario.getGamerTag());
        dto.setEmail(usuario.getEmail());
        dto.setTelefone(usuario.getTelefone());
        dto.setEndereco(usuario.getEndereco());
        dto.setCidade(usuario.getCidade());
        dto.setEstado(usuario.getEstado());
        dto.setPais(usuario.getPais());
        return dto;
    }


    public static Usuario toEntity(UsuarioDto dto) {


        Usuario usuario = new Usuario();
        usuario.setId(dto.getId()); // normalmente omitido no POST
        usuario.setNome(dto.getNome());
        usuario.setGamerTag(dto.getGamerTag());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());
        usuario.setEndereco(dto.getEndereco());
        usuario.setCidade(dto.getCidade());
        usuario.setEstado(dto.getEstado());
        usuario.setPais(dto.getPais());
        return usuario;
    }
    public static void merge(Usuario usuario, UsuarioUpdateDto dto) {
        if (dto.getNome() != null) usuario.setNome(dto.getNome());
        if (dto.getGamerTag() != null) usuario.setGamerTag(dto.getGamerTag());
        if (dto.getEmail() != null) usuario.setEmail(dto.getEmail());
        if (dto.getTelefone() != null) usuario.setTelefone(dto.getTelefone());
        if (dto.getEndereco() != null) usuario.setEndereco(dto.getEndereco());
        if (dto.getCidade() != null) usuario.setCidade(dto.getCidade());
        if (dto.getEstado() != null) usuario.setEstado(dto.getEstado());
        if (dto.getPais() != null) usuario.setPais(dto.getPais());
    }

    }

