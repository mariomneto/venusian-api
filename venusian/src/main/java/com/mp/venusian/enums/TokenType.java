package com.mp.venusian.enums;

public enum TokenType {
    BEARER("BEARER"),
    REFRESH("REFRESH");

    private String value;

    TokenType(String value) {
        this.value = value;
    }
}