package com.project.sharenter.model;

public enum Role {
    SHARER("Sharer"),
    RENTER("Renter");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
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