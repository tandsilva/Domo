package com.exozonia.domo.repository;



import com.exozonia.domo.model.Skin;
import com.exozonia.domo.model.Usuario;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface    SkinRepository extends Neo4jRepository<Skin, Long> {
    boolean existsByNome(String nome);


    // Você pode colocar consultas customizadas aqui, se precisar.
}
