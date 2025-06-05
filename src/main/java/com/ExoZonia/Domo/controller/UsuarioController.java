package com.ExoZonia.Domo.controller;

import com.ExoZonia.Domo.model.Usuario;
import com.ExoZonia.Domo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/confessar")
    public ResponseEntity<String> fazerConfissao(@RequestParam String frase, @AuthenticationPrincipal Usuario usuario) {
        try {
            service.fazerConfissao(usuario, frase);
            return ResponseEntity.ok("Confissão aceita. Bem-vindo!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario) {
        Usuario salvo = service.salvar(usuario);
        return ResponseEntity.status(201).body(salvo);
    }

    @GetMapping
    public List<Usuario> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

}
