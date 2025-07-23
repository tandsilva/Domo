package com.exozonia.domo.model;

import lombok.*;
import org.springframework.data.neo4j.core.schema.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Node("Usuario")
@Data  // Lombok já gera getters, setters, toString, equals e hashCode
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue
    private Long id;
    private String senha;
    private String nome;
    private String gamerTag;
    private String email;
    private String telefone;
    @Property("cpf")  // ou só declare, dependendo da versão do Spring Data Neo4
    private String cpf;
    private String endereco;
    private String cidade;
    private String estado;
    private String pais;
    private LocalDate dataNascimento;  // Adicionando o campo de data de nascimento
    private boolean confessou;
//
//    @Relationship(type = "TEM_AVATAR", direction = Relationship.Direction.OUTGOING)
//    private Avatar avatar;
//
//    @Relationship(type = "PARTICIPA", direction = Relationship.Direction.OUTGOING)
//    private Ambiente ambiente;
@Relationship(type = "TEM_AVATAR", direction = Relationship.Direction.OUTGOING)
@ToString.Exclude
@EqualsAndHashCode.Exclude
private transient Avatar avatar;
    @Relationship(type = "REALIZOU_LOGIN", direction = Relationship.Direction.OUTGOING)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Login> logins = new ArrayList<>();
    @Relationship(type = "POSSUI_ARMA", direction = Relationship.Direction.OUTGOING)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Weapon> armas = new ArrayList<>();

    @Relationship(type = "PARTICIPA", direction = Relationship.Direction.OUTGOING)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private transient Ambiente ambiente;
    // Método para calcular a idade
    public int getIdade() {
        if (dataNascimento == null) {
            return 0;  // Caso a data de nascimento seja nula
        }
        return Period.between(dataNascimento, LocalDate.now()).getYears();  // Calculando a idade
    }
}
