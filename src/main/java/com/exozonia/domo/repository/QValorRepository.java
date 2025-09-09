package com.exozonia.domo.repository;


import com.exozonia.domo.model.QValor;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface QValorRepository extends Neo4jRepository<QValor, Long> {
    Optional<QValor> findByChave(String chave);
}
