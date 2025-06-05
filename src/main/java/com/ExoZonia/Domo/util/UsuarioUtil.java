package com.ExoZonia.Domo.util;

import com.ExoZonia.Domo.model.Usuario;

public class UsuarioUtil {

    // Função para verificar se o usuário tem mais de 18 anos
    public static boolean isMaiorDeIdade(Usuario usuario) {
        // Aqui você implementaria a lógica para verificar a idade do usuário
        // Supondo que você tenha uma data de nascimento no modelo do usuário
        return usuario.getIdade() >= 18;
    }

    // Função para gerar uma gamerTag padrão caso o usuário não tenha definido uma
    public static String gerarGamerTagPadrao(Usuario usuario) {
        return usuario.getNome().substring(0, 3) + "123";
    }
}
