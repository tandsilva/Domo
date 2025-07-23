package com.exozonia.domo.model;


import lombok.*;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDateTime;

@Node("Login")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime dataHora;

    @Relationship(type = "FEZ_LOGIN", direction = Relationship.Direction.OUTGOING)
    private Usuario usuario;
}