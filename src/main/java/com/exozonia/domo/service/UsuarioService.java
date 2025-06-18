package com.exozonia.domo.service;

import com.exozonia.domo.model.Usuario;
import com.exozonia.domo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario salvar(Usuario usuario) {
        // Aqui você pode validar campos obrigatórios, tipo nome não nulo, email válido, etc.
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
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

//    public void fazerConfissao(Usuario usuario, String frase) {
//        String fraseCorreta = "Eu confesso que tu es Senhor Jesus o Rei dos reis senhor dos senhores";
//
//        if (frase.equalsIgnoreCase(fraseCorreta)) {
//            usuario.setConfessou(true);
//            repository.save(usuario);
//        } else {
//            throw new IllegalArgumentException("Frase incorreta. Tente novamente.");
//        }
//    }

    public void fazerConfissao(Long usuarioId, String frase) {
        String fraseCorreta = "Eu confesso que tu es Senhor Jesus o Rei dos reis senhor dos senhores";
        Usuario usuario = repository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        if (frase.equalsIgnoreCase(fraseCorreta)) {
            usuario.setConfessou(true);
            repository.save(usuario);
        } else {
            throw new IllegalArgumentException("Frase incorreta. Tente novamente.");
        }
    }

}