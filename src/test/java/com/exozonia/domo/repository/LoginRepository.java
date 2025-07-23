package com.exozonia.domo.repository;

import com.exozonia.domo.model.Login;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface LoginRepository extends Neo4jRepository<Login, Long> {
}