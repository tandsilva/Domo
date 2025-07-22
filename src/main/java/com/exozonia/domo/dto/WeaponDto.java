package com.exozonia.domo.dto;

import com.exozonia.domo.enums.AmmoType;
import com.exozonia.domo.enums.WeaponClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeaponDto {
    private Long id;
    private String name;
    private WeaponClass weaponClass;
    private AmmoType ammoType;
    private int damagePerShot;
    private Float fireRate;
    private boolean isLegendary;
    private String description;
}
