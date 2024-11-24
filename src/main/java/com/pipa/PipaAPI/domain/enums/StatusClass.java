package com.pipa.PipaAPI.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusClass {
    PLANNED("planned"),
    COMPLETED("completed");

    private final String value;

    StatusClass(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
