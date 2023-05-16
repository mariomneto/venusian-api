package com.mp.venusian.enums;

public enum Role {
    USER("USER"),
    MODERATOR("MODERATOR"),
    ADMIN("ADMIN");

    private String value;

    Role(String value) {
        this.value = value;
    }
}