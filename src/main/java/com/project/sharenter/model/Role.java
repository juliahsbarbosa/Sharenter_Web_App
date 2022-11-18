package com.project.sharenter.model;


public enum Role {
    SHARER("Sharer"),//user
    RENTER("Renter");//admin

    private final String value;

    private Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}