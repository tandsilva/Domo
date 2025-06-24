package com.exozonia.domo.controller;

import com.exozonia.domo.model.Avatar;
import com.exozonia.domo.model.Usuario;
import com.exozonia.domo.service.AvatarService;
import com.exozonia.domo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/usuarios")
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


