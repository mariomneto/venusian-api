package com.mp.venusian.enums;

public enum PostType {
    PRIVATE("PRIVATE"),
    BUBBLE("BUBBLE"),
    BEACON("BEACON");

    private String value;

    PostType(String value) {
        this.value = value;
    }
}