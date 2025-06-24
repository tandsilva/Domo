package com.exozonia.domo.controller;

import com.exozonia.domo.model.Avatar;
import com.exozonia.domo.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avatares")  // rota corrigida!
public class AvatarController {

    @Autowired
    private AvatarService avatarService;

    @PostMapping
    public ResponseEntity<Avatar> criar(@RequestBody Avatar avatar) {
        Avatar salvo = avatarService.salvar(avatar);
        return ResponseEntity.status(201).body(salvo);
    }

    @GetMapping
    public List<Avatar> listar() {
        return avatarService.listarTodos();
    }

    @GetMapping("/{id}")
    public Avatar buscarPorId(@PathVariable Long id) {
        return avatarService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        avatarService.deletar(id);
    }
}
