package com.ExoZonia.Domo.model;


import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Conhecimento")
public class Conhecimento {

    @Id
    private String pergunta;  // chave natural

    @Setter
    private String resposta;

    public Conhecimento() {}

    public Conhecimento(String pergunta, String resposta) {
        this.pergunta = pergunta.toLowerCase().trim();
        this.resposta = resposta;
    }

    // getters e setters
    public String getPergunta() { return pergunta; }
    public void setPergunta(String pergunta) { this.pergunta = pergunta.toLowerCase().trim(); }

    public String getResposta() { return resposta; }
}
