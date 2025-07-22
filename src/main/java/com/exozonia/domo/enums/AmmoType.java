package com.exozonia.domo.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AmmoType {
    LIGHT,
    HEAVY,
    ENERGY,
    SHOTGUN,
    SNIPER,
    LEGENDARY;

    @JsonCreator
    public static AmmoType fromString(String value) {
        return switch (value.toUpperCase()) {
            case "BULLET" -> HEAVY;
            default -> AmmoType.valueOf(value.toUpperCase());
        };
    }
}
