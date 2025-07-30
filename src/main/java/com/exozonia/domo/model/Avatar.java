package com.exozonia.domo.model;

import lombok.*;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;

@Node("Avatar")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avatar {

    @Id
    @GeneratedValue
    private Long id;

    private double posX;
    private double posY;

    private String pele;
    private String especies;
    private String status;
    private String nomeVisual;

    @Relationship(type = "TEM_SKIN", direction = Relationship.Direction.OUTGOING)
    private List<Skin> skins;

    @Relationship(type = "E_DONO", direction = Relationship.Direction.INCOMING)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Usuario dono;

    @Relationship(type = "AMIGO_DE")
    private List<Avatar> amigos;
}
