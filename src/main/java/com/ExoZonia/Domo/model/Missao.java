package com.ExoZonia.Domo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node("Missao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Missao {
    @Id
    @GeneratedValue
    private Long id;

    private String titulo;
    private String descricao;
    private boolean concluida;

    @Relationship(type = "ATRIBUIDA_A")
    private Usuario usuario;
}
