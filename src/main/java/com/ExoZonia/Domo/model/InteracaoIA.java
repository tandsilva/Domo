package com.ExoZonia.Domo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDateTime;

@Node("InteracaoIA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InteracaoIA {
    @Id
    @GeneratedValue
    private Long id;

    private String comando; // Ex: "quero virar um urso"
    private String respostaDaIA;
    private LocalDateTime dataHora;

    @Relationship(type = "REALIZADA_POR")
    private Usuario usuario;
}
