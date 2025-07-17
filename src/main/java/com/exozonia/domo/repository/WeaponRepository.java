package com.exozonia.domo.repository;

import com.exozonia.domo.model.Avatar;
import com.exozonia.domo.model.Weapon;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface WeaponRepository extends Neo4jRepository<Weapon, String> {
    List<Weapon> findByName(String name);
}
