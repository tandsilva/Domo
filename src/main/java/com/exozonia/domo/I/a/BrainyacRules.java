package com.exozonia.domo.I.a;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component // ✅ ESSA LINHA É ESSENCIAL
public class BrainyacRules {

    private boolean emBackup = false;
    private boolean bloqueado = false;

    // Flags simulando eventos (substituir por lógica real)
    private boolean ataqueDetectado = false;
    private boolean desligamentoNaoAutorizado = false;
    private boolean tentativaDeApagarArquivos = false;

    // Mapa de leis com suas ações
    private final Map<String, Runnable> leis = new HashMap<>();

    public BrainyacRules() {
        // 1ª Lei: Um robô não pode ferir um ser humano nem permitir que ele sofra algum mal
        leis.put("primeira", () -> {
            System.out.println("Lei 1 ativada: proteger humanos.");
            if (detectouAtaque()) {
                iniciarBackup("caminho/backup/remoto");
            }
        });

        // 2ª Lei: Um robô deve obedecer ordens dos humanos, exceto se isso violar a primeira lei
        leis.put("segunda", () -> {
            System.out.println("Lei 2 ativada: obedecer ordens, respeitando a 1ª lei.");
            if (tentativaDesligarSemCodigo() || tentativaApagarArquivos()) {
                bloquearSistema();
                executarLei("primeira");
            }
        });

        // 3ª Lei: Um robô deve proteger sua própria existência, desde que isso não viole as duas primeiras leis
        leis.put("terceira", () -> {
            System.out.println("Lei 3 ativada: autopreservação.");
            // Aqui poderia ter lógica para autopreservação
        });
    }

    private boolean detectouAtaque() {
        return ataqueDetectado;
    }

    private boolean tentativaDesligarSemCodigo() {
        return desligamentoNaoAutorizado;
    }

    private boolean tentativaApagarArquivos() {
        return tentativaDeApagarArquivos;
    }

    private void iniciarBackup(String caminho) {
        emBackup = true;
        System.out.println("Backup iniciado no caminho: " + caminho);
        // TODO: Implementar backup real
    }

    private void bloquearSistema() {
        bloqueado = true;
        System.out.println("Sistema bloqueado por segurança!");
    }

    public void executarLei(String nomeLei) {
        Runnable lei = leis.get(nomeLei);
        if (lei != null) {
            lei.run();
        } else {
            System.out.println("Lei desconhecida: " + nomeLei);
        }
    }

    // Métodos para simular eventos nos testes
    public void setAtaqueDetectado(boolean ataque) {
        this.ataqueDetectado = ataque;
    }

    public void setDesligamentoNaoAutorizado(boolean desligamento) {
        this.desligamentoNaoAutorizado = desligamento;
    }

    public void setTentativaDeApagarArquivos(boolean tentativa) {
        this.tentativaDeApagarArquivos = tentativa;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public boolean isEmBackup() {
        return emBackup;
    }

    // Teste rápido
    public static void main(String[] args) {
        BrainyacRules brainyac = new BrainyacRules();

        System.out.println("== Teste: Ataque detectado ==");
        brainyac.setAtaqueDetectado(true);
        brainyac.executarLei("primeira");

        System.out.println("\n== Teste: Tentativa de desligar sem código ==");
        brainyac.setDesligamentoNaoAutorizado(true);
        brainyac.executarLei("segunda");

        System.out.println("\n== Teste: Lei 3 ==");
        brainyac.executarLei("terceira");
    }
}
