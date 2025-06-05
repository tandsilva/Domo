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

    private String pele;
    private String especies;
    private String status;
    private String nomeVisual;

    // Relacionamento com Skin
    @Relationship(type = "TEM_SKIN", direction = Relationship.Direction.OUTGOING)
    private List<Skin> skins;

    // Relacionamento com Usuario (dono do avatar)
    @Relationship(type = "E_DONO", direction = Relationship.Direction.INCOMING)
    private Usuario dono;

    // Relacionamento sem direção, só declare o tipo, sem direction
    @Relationship(type = "AMIGO_DE")
    private List<Avatar> amigos;

}
