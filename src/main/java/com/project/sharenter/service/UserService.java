package com.project.sharenter.service;

import com.project.sharenter.dto.UserDto;
import com.project.sharenter.model.User;

import java.util.Optional;

public interface UserService {
    void registerUser(UserDto user);

}
