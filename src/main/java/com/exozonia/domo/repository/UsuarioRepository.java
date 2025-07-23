package com.exozonia.domo.repository;


import com.exozonia.domo.model.Usuario;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends Neo4jRepository<Usuario, Long> {
    // Aqui você pode criar consultas personalizadas se quiser, exemplo:
    Usuario findByGamerTag(String gamerTag);
    boolean existsByNome(String nome);
    boolean existsByEmail(String email);
    @Query("MATCH (u:Usuario) WHERE u.email = $email OR u.cpf = $cpf RETURN u LIMIT 1")
    Optional<Usuario> findByEmailOrCpf(String email, String cpf);
    Usuario findByEmail(String email);
}