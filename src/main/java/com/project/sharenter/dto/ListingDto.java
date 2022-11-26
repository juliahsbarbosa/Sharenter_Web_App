package com.project.sharenter.dto;

import com.project.sharenter.model.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ListingDto {
    @NotBlank
    private String title;

    @NotNull
    private Double rent;

    @NotNull
    private RoomType roomType;

//    @Embedded
//    private Address address;
//
//    @Embedded
//    private Geocoding geocoding;
//
//    private boolean geoProcessed = false;

    @NotNull
    private int housemates;

    private boolean privateBathroom;

    private boolean petFriendly;

    private boolean suitableForCouples;

    private boolean billsIncluded;

    private boolean parking;

    private boolean landlordOccupied;

}