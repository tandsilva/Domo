package com.exozonia.domo.model;

// Importações do Lombok para reduzir boilerplate
import lombok.*;

// Importações do Spring Data Neo4j para mapeamento de grafos
import org.springframework.data.neo4j.core.schema.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

// Define que esta classe representa um nó chamado "Usuario" no banco Neo4j
@Node("Usuario")
@Data // Lombok: gera automaticamente getters, setters, toString, equals e hashCode
@AllArgsConstructor // Lombok: gera construtor com todos os campos
@NoArgsConstructor // Lombok: gera construtor sem argumentos
public class Usuario {

    // Identificador único do nó, gerado automaticamente
    @Id
    @GeneratedValue
    private Long id;

    // Campos básicos do usuário
    private String senha;
    private String nome;
    private String gamerTag;
    private String email;
    private String telefone;

    // Mapeia o campo "cpf" com esse nome no banco de dados
    @Property("cpf")
    private String cpf;

    // Informações de localização
    private String endereco;
    private String cidade;
    private String estado;
    private String pais;

    // Data de nascimento do usuário
    private LocalDate dataNascimento;

    // Campo booleano indicando se o usuário confessou algo (lógica de jogo?)
    private boolean confessou;

    // Relacionamento com o nó Avatar (um para um)
    @Relationship(type = "TEM_AVATAR", direction = Relationship.Direction.OUTGOING)
    @ToString.Exclude // Evita loops ao gerar toString
    @EqualsAndHashCode.Exclude // Evita loops ao gerar equals/hashCode
    private transient Avatar avatar; // transient: não serializa esse campo

    // Relacionamento com múltiplos logins realizados pelo usuário
    @Relationship(type = "REALIZOU_LOGIN", direction = Relationship.Direction.OUTGOING)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Login> logins = new ArrayList<>();

    // Relacionamento com múltiplas armas que o usuário possui
    @Relationship(type = "POSSUI_ARMA", direction = Relationship.Direction.OUTGOING)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Weapon> armas = new ArrayList<>();

    // Relacionamento com o ambiente em que o usuário participa
    @Relationship(type = "PARTICIPA", direction = Relationship.Direction.OUTGOING)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private transient Ambiente ambiente;

    // Método utilitário para calcular a idade do usuário com base na data de nascimento
    public int getIdade() {
        if (dataNascimento == null) {
            return 0; // Retorna 0 se a data de nascimento não estiver definida
        }
        // Calcula a diferença entre a data de nascimento e a data atual
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
}
