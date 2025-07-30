package com.exozonia.domo.model;

import com.exozonia.domo.enums.SkinCategoria;
import com.exozonia.domo.enums.SkinRaridade;
import com.exozonia.domo.enums.SkinTipo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node("Skin")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skin {

        @Id
        @GeneratedValue
        private Long id;

        private String nome;
        private String imagemPath;

        private SkinTipo tipo;
        private SkinCategoria categoria;
        private SkinRaridade raridade;

        private String descricao;
        private String corPrincipal;     // Ex: "#FF5733" ou "Vermelho"
        private boolean animada;
        private String efeitoEspecial;
        private String modelo3DPath;
        private String dataLancamento;
        @Relationship(type = "POSSUI_SKIN", direction = Relationship.Direction.INCOMING)
        @ToString.Exclude
        @EqualsAndHashCode.Exclude
        @JsonIgnore
        private Usuario dono;// ou LocalDate, se for fazer validação com datas
}