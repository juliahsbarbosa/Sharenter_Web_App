package com.project.sharenter.model;

//Defines the types of role an user can have
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