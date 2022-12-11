package com.project.sharenter.dto;

import com.project.sharenter.model.Listing;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class InquiryDto {

    private long id;

    @NotBlank(message = "Please enter your name")
    private String Name;

    @Email(message = "Please enter a valid email format")
    @NotBlank(message = "Please enter your name")
    private String email;

    @NotBlank(message = "Please enter your phone number")
    private String phone;

    @NotBlank(message = "Please enter your inquiry")
    private String inquiry;

    private Listing listing;
}
