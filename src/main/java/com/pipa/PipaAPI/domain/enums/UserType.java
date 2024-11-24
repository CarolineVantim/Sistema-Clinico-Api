package com.pipa.PipaAPI.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserType {
    ADMIN("admin"), // para os desenvolvedores da aplicação e diretoria da escola
    FAMILY("family"),
    PROFESSIONAL("professional"),
    FAMILY_PROFESSIONAL("family_professional"); // para casos em que um profissional tenha um filho matriculado

    private final String value;

    UserType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
