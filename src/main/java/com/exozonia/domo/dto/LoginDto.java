package com.exozonia.domo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@Data
@NoArgsConstructor
@AllArgsConstructor

    public class LoginDto {
        private String email;
        private String cpf;
        private String senha;
    }

