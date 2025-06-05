package com.ExoZonia.Domo.service;

import com.ExoZonia.Domo.model.Usuario;
import com.ExoZonia.Domo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    // Novo método para fazer a confissão
    public void fazerConfissao(Usuario usuario, String frase) {
        String fraseCorreta = "Eu confesso que tu es Senhor Jesus o Rei dos reis senhor dos senhores";

        if (frase.equalsIgnoreCase(fraseCorreta)) {
            usuario.setConfessou(true);
            repository.save(usuario);
        } else {
            throw new IllegalArgumentException("Frase incorreta. Tente novamente.");
        }
    }
}
