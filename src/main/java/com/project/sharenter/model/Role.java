package com.project.sharenter.model;

import lombok.*;

public enum Role {
    SHARER("Sharer"),//user
    RENTER("Renter");//admin

    private final String name;

    private Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

//    public static Role parse(String id) {
//        Role role = null; // Default
//        for (Role item : Role.values()) {
//            if (item.name.equals(id)) {
//                role = item;
//                break;
//            }
//        }
//        return role;
//    }
}