package com.exozonia.domo.controller;

import com.exozonia.domo.dto.WeaponDto;
import com.exozonia.domo.mapper.WeaponMapper;
import com.exozonia.domo.model.Weapon;
import com.exozonia.domo.service.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/weapons")
public class WeaponController {

    @Autowired
    private WeaponService weaponService;

    @PostMapping
    public ResponseEntity<String> criarWeapon(@RequestBody WeaponDto dto) {
        try {
            Weapon weapon = WeaponMapper.toEntity(dto);
            weaponService.salvar(weapon);
            return ResponseEntity.ok("Arma criada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao criar arma.");
        }
    }

    @PostMapping("/associar-skin")
    public ResponseEntity<String> associarSkin(
            @RequestParam Long weaponId,
            @RequestParam Long skinId
    ) {
        boolean sucesso = weaponService.associarSkin(weaponId, skinId);
        if (sucesso) {
            return ResponseEntity.ok("Skin associada à arma com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Falha ao associar skin.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeaponDto> buscarPorId(@PathVariable Long id) {
        Weapon weapon = weaponService.buscarPorId(id);
        if (weapon == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(WeaponMapper.toDto(weapon));
    }

    @GetMapping
    public ResponseEntity<List<WeaponDto>> listar() {
        List<WeaponDto> lista = weaponService.listarTodos()
                .stream()
                .map(WeaponMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(
            @PathVariable Long id,
            @RequestBody WeaponDto dto
    ) {
        Weapon weapon = WeaponMapper.toEntity(dto);  // converte DTO para entidade
        boolean sucesso = weaponService.atualizar(id, weapon);
        if (sucesso) return ResponseEntity.ok("Arma atualizada.");
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        boolean sucesso = weaponService.deletar(id);
        if (sucesso) return ResponseEntity.ok("Arma deletada.");
        return ResponseEntity.notFound().build();
    }
}
