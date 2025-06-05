package com.ExoZonia.Domo.model;

@Node("Missao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Missao {
    @Id @GeneratedValue
    private Long id;

    private String titulo;
    private String descricao;
    private boolean concluida;

    @Relationship(type = "ATRIBUIDA_A")
    private Usuario usuario;
}
