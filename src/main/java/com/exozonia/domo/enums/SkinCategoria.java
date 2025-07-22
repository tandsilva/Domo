package com.exozonia.domo.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum SkinCategoria {
    AVATAR,
    ARMA,
    AMBOS;

    @JsonCreator
    public static SkinCategoria fromString(String value) {
        return SkinCategoria.valueOf(value.toUpperCase());
    }
}
