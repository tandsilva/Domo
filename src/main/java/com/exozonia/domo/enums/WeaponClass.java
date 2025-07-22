package com.exozonia.domo.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum WeaponClass {
    ASSAULT_RIFLE,
    SMG,
    LMG,
    SNIPER,
    MARKSMAN,
    SHOTGUN,
    PISTOL;

    @JsonCreator
    public static WeaponClass fromString(String value) {
        return WeaponClass.valueOf(value.toUpperCase());
    }

}
