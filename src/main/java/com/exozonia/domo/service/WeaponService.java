package com.exozonia.domo.service;

import com.exozonia.domo.model.Skin;
import com.exozonia.domo.model.Weapon;
import com.exozonia.domo.repository.SkinRepository;
import com.exozonia.domo.repository.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeaponService {

    @Autowired
    private SkinRepository skinRepository;

    @Autowired
    private WeaponRepository weaponRepository;

    public Weapon salvar(Weapon weapon) {
        // Aqui você pode colocar validações se quiser antes de salvar
        return weaponRepository.save(weapon);
    }

    public List<Weapon> listarTodos() {
        return weaponRepository.findAll();
    }

    public Weapon buscarPorId(Long id) {
        return weaponRepository.findById(id).orElse(null);
    }

    public boolean deletar(Long id) {
        if (weaponRepository.existsById(id)) {
            weaponRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean atualizar(Long id, Weapon weaponAtualizado) {
        Weapon weapon = weaponRepository.findById(id).orElse(null);
        if (weapon == null) return false;

        // Atualiza os campos da entidade com os valores do objeto passado
        weapon.setName(weaponAtualizado.getName());
        weapon.setWeaponClass(weaponAtualizado.getWeaponClass());
        weapon.setAmmoType(weaponAtualizado.getAmmoType());
        weapon.setDamagePerShot(weaponAtualizado.getDamagePerShot());
        weapon.setFireRate(weaponAtualizado.getFireRate());
        weapon.setLegendary(weaponAtualizado.isLegendary());
        weapon.setDescription(weaponAtualizado.getDescription());
        weapon.setSkins(weaponAtualizado.getSkins());

        weaponRepository.save(weapon);
        return true;
    }

    public boolean associarSkin(Long weaponId, Long skinId) {
        Weapon weapon = weaponRepository.findById(weaponId).orElse(null);
        Skin skin = skinRepository.findById(skinId).orElse(null);

        if (weapon == null || skin == null) {
            return false;
        }

        if (weapon.getSkins() == null) {
            weapon.setSkins(new ArrayList<>());
        }
        weapon.getSkins().add(skin);

        weaponRepository.save(weapon);
        return true;
    }
}
