package com.exozonia.domo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Sentimento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sentimento {

    @Id
    @GeneratedValue
    private Long id;

    private String tipo;   // positivo, negativo, neutro
    private String lingua; // opcional: "es", "en", etc.
}
