package com.mp.venusian.enums;

public enum Role {

    ADMIN("ADMIN"),
    INVESTOR("INVESTOR");

    private String value;

    Role(String value) {
        this.value = value;
    }
}