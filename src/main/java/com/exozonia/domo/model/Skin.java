
package com.exozonia.domo.model;

import com.exozonia.domo.model.Avatar;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Node("Skin")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skin {




        @Id
        @GeneratedValue
        @EqualsAndHashCode.Include
        private Long id;

        private String imagemPath;
        private String nome;
        private String cor;

        // @Relationship(type = "TEM_SKIN", direction = Relationship.Direction.INCOMING)
        // private Avatar avatar;
    }

