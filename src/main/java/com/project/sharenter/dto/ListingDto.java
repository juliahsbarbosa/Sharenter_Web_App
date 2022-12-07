package com.project.sharenter.dto;

import com.project.sharenter.model.*;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

//Dto with validation for creating a new listing form
@Data
public class ListingDto {

    private Long Id;

    @NotBlank(message = "Please enter a title for your Listing")
    private String title;

    @NotNull (message = "Please enter the monthly rent")
    @DecimalMin(message = "Value must be greater than 300", value = "100")
    private BigDecimal rent;

    @NotNull(message = "You must select the type of room you are listing")
    private RoomType roomType;

//    @NotBlank(message = "Please search and enter the house address")
    private String address;

    @NotBlank(message = "Please enter the URL for an image of the room")
    private String imageUrl;

    @NotNull(message = "Please inform how many people are in the house")
    private int numHousemates;

    private boolean privateBathroom;

    private boolean petFriendly;

    private boolean suitableForCouples;

    private boolean billsIncluded;

    private boolean parking;

    private boolean landlordOccupied;

}