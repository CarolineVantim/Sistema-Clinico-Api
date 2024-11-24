package com.pipa.PipaAPI.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    M("masculino"),
    F("feminino"),
    O("outros");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
