package com.project.sharenter.dto;

import com.project.sharenter.model.Role;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.*;

//User DTO (Data Transfer Object) to transfer form data between the controller layer and view layer,
//Improves security and reduces data leaks
@Data
public class UserDto {
    @NotBlank(message = "Please enter your first name")
    private String firstName;

    @NotBlank(message = "Please enter your last name")
    private String lastName;

    @NotBlank(message = "Please enter your email")
    @Email(message = "Please enter a valid email format, e.g email@example.com")
    private String email;

    @NotBlank(message = "Please enter your password")
    @Size(min = 8, message = "Password should have at least 8 characters")
//    @Pattern(regexp = "^((?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])){8,12}$",
//            message = "The password must have at least 8 characters, with at least 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
    private String password;

    @NotBlank(message = "Please enter your phone number")
    @Pattern(regexp = "^[0-9]{3}-[0-9]{3}-[0-9]{4}$", message = "Phone number must have 10 digits and follow the format 999-999-9999")
    private String phone;


    @NotNull(message = "You must select the type of account you are creating")
    private Role role;




}
