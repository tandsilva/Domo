package com.ExoZonia.Domo.repository;

import com.ExoZonia.Domo.model.Conhecimento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConhecimentoRepository extends CrudRepository<Conhecimento, String> {
    // Nada a mais por enquanto, já pode buscar por ID que é a pergunta
}
