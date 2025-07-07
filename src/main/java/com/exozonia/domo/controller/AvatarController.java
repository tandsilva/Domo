package com.exozonia.domo.controller;

import com.exozonia.domo.dto.AvatarDto;
import com.exozonia.domo.mapper.AvatarMapper;
import com.exozonia.domo.model.Avatar;
import com.exozonia.domo.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/avatares")
public class AvatarController {

    @Autowired
    private AvatarService avatarService;

    @PostMapping
    public ResponseEntity<AvatarDto> criar(@RequestBody AvatarDto dto) {
        // Converte DTO para entidade
        Avatar entidade = AvatarMapper.toEntity(dto);
        Avatar salvo = avatarService.salvar(entidade);
        // Converte entidade salva de volta para DTO
        AvatarDto resposta = AvatarMapper.toDto(salvo);
        return ResponseEntity.status(201).body(resposta);
    }

    @GetMapping
    public ResponseEntity<List<AvatarDto>> listar() {
        List<Avatar> lista = avatarService.listarTodos();
        List<AvatarDto> dtos = lista.stream()
                .map(AvatarMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvatarDto> buscarPorId(@PathVariable Long id) {
        Avatar entidade = avatarService.buscarPorId(id);
        if (entidade == null) {
            return ResponseEntity.notFound().build();
        }
        AvatarDto dto = AvatarMapper.toDto(entidade);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        avatarService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
