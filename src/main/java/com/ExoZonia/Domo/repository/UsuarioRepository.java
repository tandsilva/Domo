package com.ExoZonia.Domo.repository;


import com.ExoZonia.Domo.model.Usuario;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends Neo4jRepository<Usuario, Long> {
    // Aqui você pode criar consultas personalizadas se quiser, exemplo:
    Usuario findByGamerTag(String gamerTag);

}