package com.project.sharenter.service;

import com.project.sharenter.dto.UserDto;
import com.project.sharenter.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    public void registerUser(UserDto user);
    public boolean isUserRegistered(UserDto user);
}
