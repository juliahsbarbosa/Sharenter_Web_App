package com.project.sharenter.dto;

import com.project.sharenter.model.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

//Listing DTO (Data Transfer Object) to transfer form data between the controller layer and view layer,
//Improves security and reduces data leaks
@Data
public class ListingDto {

    //Id added to handle editing a listing
    private Long Id;

    @NotBlank(message = "Please enter a title for your Listing")
    private String title;

    @NotBlank(message = "Please enter a description for your Listing")
    @Length(min= 200, message = "Description must contain at least 200 characters")
    private String description;

    @NotNull (message = "Please enter the monthly rent")
    @DecimalMin(message = "Value must be greater than 300", value = "300")
    private BigDecimal rent;

    @NotNull(message = "You must select the type of room you are listing")
    private RoomType roomType;

    @NotBlank(message = "Please search and enter the house address")
    private String address;

    @NotBlank(message = "Please enter the URL for an image of the room")
    private String imageUrl;

    @Min(value = 0, message = "Value must be valid!")
    @NotNull(message = "Please inform how many people are in the house")
    private int numHousemates;

    private boolean privateBathroom;

    private boolean petFriendly;

    private boolean suitableForCouples;

    private boolean billsIncluded;

    private boolean parking;

    private boolean landlordOccupied;
}