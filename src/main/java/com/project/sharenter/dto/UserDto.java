package com.project.sharenter.dto;

import com.project.sharenter.model.Role;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@RequiredArgsConstructor
public class UserDto {
    @NotBlank(message = "Please enter your first name")
    private String firstName;

    @NotBlank(message = "Please enter your last name")
    private String lastName;

    @NotEmpty(message = "Please enter your email")
    @Email
    private String email;

    @NotBlank(message = "Please enter your password")
    @Length(min = 8, message = "Password should have at least 8 characters")
    private String password;

    @NotBlank(message = "Please enter your phone number")
    private String phone;

    private Role role;




}
