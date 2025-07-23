package com.exozonia.domo.mapper;

// Importa os DTOs e a entidade que serão usados na conversão
import com.exozonia.domo.dto.UsuarioDto;
import com.exozonia.domo.dto.UsuarioUpdateDto;
import com.exozonia.domo.model.Usuario;

/**
 * Classe utilitária responsável por converter entre a entidade Usuario
 * e seus respectivos DTOs (Data Transfer Objects).
 */
public class UsuarioMapper {

    /**
     * Converte um objeto Usuario (entidade) para UsuarioDto.
     * Usado geralmente para retornar dados ao cliente sem expor tudo.
     */
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

    /**
     * Converte um UsuarioDto para a entidade Usuario.
     * Útil em operações de criação (POST) ou leitura de dados recebidos.
     */
    public static Usuario toEntity(UsuarioDto dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId()); // Em POST, normalmente esse campo é ignorado
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

    /**
     * Atualiza campos de um objeto Usuario com base nos dados de um UsuarioUpdateDto.
     * Apenas os campos não nulos no DTO serão atualizados.
     */
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
