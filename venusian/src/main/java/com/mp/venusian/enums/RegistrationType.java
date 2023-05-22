package com.mp.venusian.enums;

public enum RegistrationType {
    EMAIL("EMAIL"),
    PHONE("PHONE");

    private String value;

    RegistrationType(String value) {
        this.value = value;
    }
}