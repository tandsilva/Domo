package com.ExoZonia.Domo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node("Avatar")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avatar {
    @Id
    @GeneratedValue
    private Long id;

    private String skin;
    private String especie; // humano, urso, robô, etc.
    private String status; // online, offline, em jogo
    private String nomeVisual;

    @Relationship(type = "TEM", direction = Relationship.Direction.OUTGOING)
    private List<Avatar> avatares;
    private Usuario dono;
}
