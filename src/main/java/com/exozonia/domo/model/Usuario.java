package com.exozonia.domo.model;

import lombok.*;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDate;
import java.time.Period;

@Node("Usuario")
@Data  // Lombok já gera getters, setters, toString, equals e hashCode
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;
    private String gamerTag;
    private String email;
    private String telefone;
    private String endereco;
    private String cidade;
    private String estado;
    private String pais;
    private LocalDate dataNascimento;  // Adicionando o campo de data de nascimento
    private boolean confessou;

    @Relationship(type = "TEM_AVATAR", direction = Relationship.Direction.OUTGOING)
    private Avatar avatar;

    @Relationship(type = "PARTICIPA", direction = Relationship.Direction.OUTGOING)
    private Ambiente ambiente;
    // Método para calcular a idade
    public int getIdade() {
        if (dataNascimento == null) {
            return 0;  // Caso a data de nascimento seja nula
        }
        return Period.between(dataNascimento, LocalDate.now()).getYears();  // Calculando a idade
    }
}
