package com.exozonia.domo.mapper;

import com.exozonia.domo.dto.WeaponDto;
import com.exozonia.domo.model.Weapon;

public class WeaponMapper {

    public static Weapon toEntity(WeaponDto dto) {
        Weapon weapon = new Weapon();
        weapon.setId(dto.getId());
        weapon.setName(dto.getName());
        weapon.setWeaponClass(dto.getWeaponClass());
        weapon.setAmmoType(dto.getAmmoType());
        weapon.setDamagePerShot(dto.getDamagePerShot());
        weapon.setFireRate(dto.getFireRate());
        weapon.setLegendary(dto.isLegendary());
        weapon.setDescription(dto.getDescription());
        return weapon;
    }

    public static WeaponDto toDto(Weapon weapon) {
        WeaponDto dto = new WeaponDto();
        dto.setId(weapon.getId());
        dto.setName(weapon.getName());
        dto.setWeaponClass(weapon.getWeaponClass());
        dto.setAmmoType(weapon.getAmmoType());
        dto.setDamagePerShot(weapon.getDamagePerShot());
        dto.setFireRate(weapon.getFireRate());
        dto.setLegendary(weapon.isLegendary());
        dto.setDescription(weapon.getDescription());
        return dto;
    }


}

