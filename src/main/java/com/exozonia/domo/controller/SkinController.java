package com.exozonia.domo.controller;

import com.exozonia.domo.service.AvatarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public class SkinController {
    private AvatarService avatarService;

    @PostMapping("/{id}/skins/upload")
    public ResponseEntity<String> adicionarSkinComImagem(
            @PathVariable Long id,
            @RequestParam("nome") String nome,
            @RequestParam("cor") String cor,
            @RequestParam("imagem") MultipartFile imagem) {

        boolean sucesso = avatarService.adicionarSkinAoAvatar(id, nome, cor, imagem);

        if (sucesso) {
            return ResponseEntity.ok("Skin criada com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Erro ao criar skin ou skin já existe.");
        }
    }

}
