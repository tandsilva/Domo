package com.ExoZonia.Domo.I.a;


import org.springframework.stereotype.Component;

@Component
public class Brainyac {

    public String pensar(String entrada) {
        // Exemplo bobo de lógica "inteligente"
        if (entrada.toLowerCase().contains("onde está o trem")) {
            return "A estação está no limbo, entre dois mundos. Deseja embarcar?";
        }

        return "Ainda estou aprendendo... me alimente com mais dados.";
    }
}
