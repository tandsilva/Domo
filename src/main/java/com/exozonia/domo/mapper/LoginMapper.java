package com.exozonia.domo.mapper;



import com.exozonia.domo.dto.LoginDto;
import com.exozonia.domo.model.Usuario;

public class LoginMapper {

    // Converte LoginDto para Usuario (para validar login, por exemplo)
    public static Usuario toEntity(LoginDto dto) {
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        return usuario;
    }

    // Se quiser converter Usuario para LoginDto (geralmente não necessário)
    public static LoginDto toDto(Usuario usuario) {
        LoginDto dto = new LoginDto();
        dto.setEmail(usuario.getEmail());
        // NUNCA exponha senha no DTO na vida real
        return dto;
    }
}
