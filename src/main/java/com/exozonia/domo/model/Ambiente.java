package com.exozonia.domo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node("Ambiente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ambiente {
    @Id
    @GeneratedValue
    private Long id;

    private String nome;
    private String descricao;
    private String tipo; // ex: florestal, urbano, cibernético

    @Relationship(type = "PARTICIPA", direction = Relationship.Direction.INCOMING)
    private List<Usuario> usuarios;
}
