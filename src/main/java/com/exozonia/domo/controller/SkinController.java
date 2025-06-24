package com.exozonia.domo.controller;

import com.exozonia.domo.dto.SkinDto;
import com.exozonia.domo.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/skins")
public class SkinController {

    @Autowired
    private AvatarService avatarService;

    @PostMapping("/{id}/upload")
    public ResponseEntity<String> adicionarSkinComImagem(
            @PathVariable Long id,
            @RequestParam("nome") String nome,
            @RequestParam("cor") String cor,
            @RequestParam("imagem") MultipartFile imagem) {

        // Montar SkinDto com os dados recebidos
        SkinDto dto = new SkinDto();
        dto.setNome(nome);
        dto.setCor(cor);

        // Enviar o DTO e a imagem para o service
        boolean sucesso = avatarService.adicionarSkinAoAvatar(id, dto, imagem);

        if (sucesso) {
            return ResponseEntity.ok("Skin criada com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Erro ao criar skin ou skin já existe.");
        }
    }
    @PutMapping("/{skinId}")
    public ResponseEntity<String> atualizarSkin(
            @PathVariable Long skinId,
            @RequestParam("nome") String nome,
            @RequestParam("cor") String cor) {

        boolean atualizado = avatarService.atualizarSkin(skinId, nome, cor);
        if (atualizado) {
            return ResponseEntity.ok("Skin atualizada com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{skinId}")
    public ResponseEntity<String> deletarSkin(@PathVariable Long skinId) {
        boolean deletado = avatarService.deletarSkinPorId(skinId);
        if (deletado) {
            return ResponseEntity.ok("Skin deletada com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
