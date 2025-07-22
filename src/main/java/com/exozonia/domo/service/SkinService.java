package com.exozonia.domo.service;

import com.exozonia.domo.enums.SkinTipo;
import com.exozonia.domo.model.Skin;
import com.exozonia.domo.repository.SkinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkinService {

    @Autowired
    private SkinRepository repository;

    public Skin salvar(Skin skin) {
        if (skin.getNome() == null || skin.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }

        if (repository.existsByNome(skin.getNome())) {
            throw new IllegalArgumentException("Nome já cadastrado");
        }

        return repository.save(skin);
    }

    public List<Skin> listarTodos() {
        return repository.findAll();
    }

    public Skin buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public boolean deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
    public boolean atualizar(Long id, String nome, String tipo) {
        Skin skin = repository.findById(id).orElse(null);
        if (skin == null) return false;

        skin.setNome(nome);

        try {
            SkinTipo tipoEnum = SkinTipo.valueOf(tipo.toUpperCase());
            skin.setTipo(tipoEnum);
        } catch (IllegalArgumentException e) {
            return false; // tipo inválido
        }

        repository.save(skin);
        return true;
    }

//    public boolean atualizar(Long id, String nome, String cor) {
//        Skin skin = repository.findById(id).orElse(null);
//        if (skin == null) return false;
//
//        skin.setNome(nome);
//
//        repository.save(skin);
//        return true;
//    }
}
