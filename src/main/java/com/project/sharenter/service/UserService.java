package com.project.sharenter.service;

import com.project.sharenter.dto.UserDto;
import com.project.sharenter.model.User;

import java.util.Optional;

public interface UserService {
    //Registers a user
    void registerUser(UserDto user);
    //Find user by its email
    Optional<User> findUserByEmail(String email);

//    Optional<User> findUserByRoleAndEmail(String role, String email);

    }
