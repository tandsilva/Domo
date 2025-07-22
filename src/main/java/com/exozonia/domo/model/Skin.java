package com.exozonia.domo.model;

import com.exozonia.domo.enums.SkinCategoria;
import com.exozonia.domo.enums.SkinRaridade;
import com.exozonia.domo.enums.SkinTipo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;

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
        private String dataLancamento;   // ou LocalDate, se for fazer validação com datas
}
