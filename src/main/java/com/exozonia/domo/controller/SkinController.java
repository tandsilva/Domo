package com.exozonia.domo.controller;

import com.exozonia.domo.dto.AssociarSkinDto;
import com.exozonia.domo.dto.SkinDto;
import com.exozonia.domo.mapper.SkinMapper;
import com.exozonia.domo.model.Skin;
import com.exozonia.domo.service.AvatarService;
import com.exozonia.domo.service.SkinService;
import com.exozonia.domo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private UsuarioService usuarioService;

    // POST para associar Skin a Avatar
    @PostMapping("/associar-avatar")
    public ResponseEntity<String> adicionarSkinAoAvatar(@RequestBody SkinDto dto) {
        boolean sucesso = avatarService.adicionarSkinAoAvatar(dto.getAvatarId(), dto.getId());

        if (sucesso) {
            return ResponseEntity.ok("Skin associada ao avatar com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Erro ao associar skin ou skin já existe.");
        }
    }

    // POST para criar uma nova Skin
    @PostMapping
    public ResponseEntity<String> criarSkin(@RequestBody SkinDto dto) {
        try {
            Skin skin = SkinMapper.toEntity(dto);
            skinService.salvar(skin);
            return ResponseEntity.ok("Skin criada com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno ao criar skin.");
        }
    }

    // GET por id
    @GetMapping("/{id}")
    public ResponseEntity<SkinDto> buscarPorId(@PathVariable Long id) {
        Skin skin = skinService.buscarPorId(id);
        if (skin == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(SkinMapper.toDto(skin));
    }

    // GET todos
    @GetMapping
    public ResponseEntity<List<SkinDto>> listar() {
        List<SkinDto> skins = skinService.listarTodos()
                .stream()
                .map(SkinMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(skins);
    }

    // PUT para atualizar Skin
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

    // DELETE Skin
    @DeleteMapping("/{skinId}")
    public ResponseEntity<String> deletarSkin(@PathVariable Long skinId) {
        boolean deletado = skinService.deletar(skinId);
        if (deletado) {
            return ResponseEntity.ok("Skin deletada com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST para associar Skin a Usuario (perfil)
    @PostMapping("/associar-usuario")
    public ResponseEntity<String> associarSkinAoUsuario(@RequestBody AssociarSkinDto dto) {
        boolean sucesso = usuarioService.associarSkin(dto.getUsuarioId(), dto.getSkinId());
        if (sucesso) {
            return ResponseEntity.ok("Skin associada ao usuário com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário ou skin não encontrados.");
        }
    }
}
