package com.exozonia.domo.controller;

import com.exozonia.domo.dto.UsuarioDto;
import com.exozonia.domo.mapper.UsuarioMapper;
import com.exozonia.domo.model.Usuario;
import com.exozonia.domo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/confessar")
    public ResponseEntity<String> fazerConfissao(
            @RequestParam String frase,
            @AuthenticationPrincipal Usuario usuario) {
        try {
            if (usuario == null) {
                return ResponseEntity.status(401).body("Usuário não autenticado.");
            }

            service.fazerConfissao(usuario.getId(), frase);
            return ResponseEntity.ok("Confissão aceita. Bem-vindo!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> criar(@RequestBody UsuarioDto usuario) {
        Usuario entity = UsuarioMapper.toEntity(usuario);
        Usuario salvo = service.salvar(entity);
        return ResponseEntity.status(201).body(UsuarioMapper.toDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listar() {
        List<UsuarioDto> usuarios = service.listarTodos()
                .stream()
                .map(UsuarioMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UsuarioDto> buscarPorId(@PathVariable Long id) {
        Usuario usuario = service.buscarPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(UsuarioMapper.toDto(usuario));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
