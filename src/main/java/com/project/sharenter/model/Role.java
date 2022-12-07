package com.project.sharenter.model;

//This class implements the user role
public enum Role {
    ROLE_SHARER("Sharer"),
    ROLE_RENTER("Renter");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}