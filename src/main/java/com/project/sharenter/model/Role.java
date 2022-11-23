package com.project.sharenter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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