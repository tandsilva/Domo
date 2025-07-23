package com.exozonia.domo.service;

import com.exozonia.domo.dto.LoginDto;
import com.exozonia.domo.model.Login;
import com.exozonia.domo.model.Usuario;
import com.exozonia.domo.repository.LoginRepository;
import com.exozonia.domo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UsuarioRepository usuarioRepository;
    private final LoginRepository loginRepository;

    public void login(LoginDto dto) {
        validarEmailOuCpf(dto);

        Usuario usuario = usuarioRepository.findByEmailOrCpf(dto.getEmail(), dto.getCpf())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        // Validação de senha, se quiser
        // if (!usuario.getSenha().equals(dto.getSenha())) throw new IllegalArgumentException("Senha inválida");

        Login login = new Login();
        login.setDataHora(LocalDateTime.now());
        login.setUsuario(usuario);

        loginRepository.save(login);
    }

    private void validarEmailOuCpf(LoginDto dto) {
        if ((dto.getEmail() == null || dto.getEmail().isBlank()) &&
                (dto.getCpf() == null || dto.getCpf().isBlank())) {
            throw new IllegalArgumentException("Informe o email ou o CPF.");
        }

        if (dto.getEmail() != null && !dto.getEmail().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Email inválido.");
        }

        if (dto.getCpf() != null && !isCpfValido(dto.getCpf())) {
            throw new IllegalArgumentException("CPF inválido.");
        }
    }

    private boolean isCpfValido(String cpf) {
        // Implementar aqui a validação de CPF real
        return cpf.matches("\\d{11}"); // Simplesmente valida o formato, não o dígito verificador
    }
}