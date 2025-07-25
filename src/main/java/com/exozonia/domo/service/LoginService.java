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
        if (cpf == null || !cpf.matches("\\d{11}") || cpf.chars().distinct().count() == 1) {
            return false;
        }

        int[] pesos1 = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] pesos2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

        int soma1 = 0, soma2 = 0;

        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cpf.charAt(i));
            soma1 += digito * pesos1[i];
            soma2 += digito * pesos2[i];
        }

        int dv1 = calcularDigitoVerificador(soma1);
        soma2 += dv1 * pesos2[9];
        int dv2 = calcularDigitoVerificador(soma2);

        return dv1 == Character.getNumericValue(cpf.charAt(9)) &&
                dv2 == Character.getNumericValue(cpf.charAt(10));
    }

    private int calcularDigitoVerificador(int soma) {
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }

}