package com.exozonia.domo.model;



import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;

@Node("QValor")
@Data
public class QValor {
    @Id
    @GeneratedValue
    private Long id;

    private String chave; // formato: "pergunta:resposta"
    private Double valor;
}
