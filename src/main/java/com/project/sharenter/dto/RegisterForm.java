//package com.project.sharenter.dto;
//
//import com.project.sharenter.model.Role;
//import org.hibernate.validator.constraints.Length;
//
//import javax.persistence.*;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotEmpty;
//
//public class RegisterForm {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotBlank(message = "Please enter your first name")
//    @Column(name = "first_name")
//    private String firstName;
//
//    @NotBlank(message = "Please enter your last name")
//    @Column(name = "last_name")
//    private String lastName;
//
//    @NotEmpty(message = "Please enter your email")
//    @Email
//    @Column(name = "email", unique = true)
//    private String email;
//
//    @NotBlank(message = "Please enter your password")
//    @Length(min = 8, message = "Password should have at least 8 characters")
//    @Column(name = "password")
//    private String password;
//
//    @Column(name = "phone")
//    @NotBlank(message = "Please enter your phone number")
//    private String phone;
//
//    @Enumerated(EnumType.STRING)
//    private Role role;
//
//    @Column(name = "locked")
//    private Boolean locked = false;
//
//    @Column(name = "enabled")
//    private Boolean enabled = true;
//}
