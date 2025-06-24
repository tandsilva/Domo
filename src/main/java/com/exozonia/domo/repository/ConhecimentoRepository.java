package com.exozonia.domo.repository;

import com.exozonia.domo.model.Conhecimento;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

    @Repository
    public interface ConhecimentoRepository extends Neo4jRepository<Conhecimento, String> {
//        Optional<Conhecimento> findByPergunta(String pergunta);
    }
