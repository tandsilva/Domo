package com.exozonia.domo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node("Skin")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skin {

    @Id
    @GeneratedValue
    private Long id;
    private String imagemPath; // caminho da imagem salva

    private String nome; // Nome da skin (ex: "Skin do Robô", "Skin de Urso")

    private String cor;  // Cor da skin, ou algum outro atributo visual

    // Relacionamento com Avatar: uma skin pertence a um avatar
    @Relationship(type = "TEM_SKIN", direction = Relationship.Direction.INCOMING)
    private Avatar avatar;
}
