package com.exozonia.domo.controller;

import com.exozonia.domo.dto.SkinDto;
import com.exozonia.domo.mapper.SkinMapper;
import com.exozonia.domo.service.AvatarService;
import com.exozonia.domo.service.SkinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/skins")
public class SkinController {

    @Autowired
    private AvatarService avatarService;

    @Autowired
    private SkinService skinService;

    @PostMapping("/associar")
    public ResponseEntity<String> adicionarSkinAoAvatar(@RequestBody SkinDto dto) {
        boolean sucesso = avatarService.adicionarSkinAoAvatar(dto.getAvatarId(), dto.getId());

        if (sucesso) {
            return ResponseEntity.ok("Skin associada com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Erro ao associar skin ou skin já existe.");
        }
    }

    @GetMapping
    public ResponseEntity<List<SkinDto>> listar() {
        List<SkinDto> skins = skinService.listarTodos()
                .stream()
                .map(SkinMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(skins);
    }

    @PutMapping("/{skinId}")
    public ResponseEntity<String> atualizarSkin(
            @PathVariable Long skinId,
            @RequestParam("nome") String nome,
            @RequestParam("cor") String cor) {

        boolean atualizado = skinService.atualizar(skinId, nome, cor);
        if (atualizado) {
            return ResponseEntity.ok("Skin atualizada com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{skinId}")
    public ResponseEntity<String> deletarSkin(@PathVariable Long skinId) {
        boolean deletado = skinService.deletar(skinId);
        if (deletado) {
            return ResponseEntity.ok("Skin deletada com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
