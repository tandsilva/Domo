package com.exozonia.domo.repository;

import com.exozonia.domo.model.Avatar;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AvatarRepository extends Neo4jRepository<Avatar, Long> {
    // Aqui você pode criar consultas personalizadas se quiser, exemplo:
    List<Avatar> findByEspecies(String especies);


}