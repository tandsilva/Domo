package com.exozonia.domo.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum SkinRaridade {
    COMUM,
    RARA,
    EPICA,
    LENDARIA,
    MITICA;

    @JsonCreator
    public static SkinRaridade fromString(String value) {
        return SkinRaridade.valueOf(value.toUpperCase());
    }
}

