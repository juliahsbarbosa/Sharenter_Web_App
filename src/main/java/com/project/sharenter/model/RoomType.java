package com.project.sharenter.model;

//Defines the type of room type a listing can have
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
