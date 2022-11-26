package com.project.sharenter.model;

public enum RoomType {
    Single("Single"),
    Double("Double"),
    Twin("Twin"),
    Triple("Triple");

    private final String value;

    RoomType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
