package com.project.sharenter.dto;

import com.project.sharenter.model.Listing;
import com.project.sharenter.model.Role;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ListingDto {
    @NotBlank
    private String title;

    private Double rent;

    private Boolean landlordOccupied;

    public boolean isLandlordOccupied() {
        return landlordOccupied;
    }



}