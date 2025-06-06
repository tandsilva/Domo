package com.ExoZonia.Domo.service;



import com.ExoZonia.Domo.I.a.Brainyac;
import com.ExoZonia.Domo.I.a.BrainyacRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrainyacService {

    @Autowired
    private Brainyac brainyac;
    private final BrainyacRules rules;

    public BrainyacService(BrainyacRules rules) {
        this.rules = rules;
    }

    public void executarTodasAsLeis() {
        rules.executarLei("primeira");
        rules.executarLei("segunda");
        rules.executarLei("terceira");
    }

    public void executarLeiEspecifica(String lei) {
        rules.executarLei(lei.toLowerCase());
    }

    public boolean estaBloqueado() {
        return rules.isBloqueado();
    }

    public boolean estaEmBackup() {
        return rules.isEmBackup();
    }
}
