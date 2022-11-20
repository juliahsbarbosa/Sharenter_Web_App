package com.project.sharenter.service;

import com.project.sharenter.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    public void registerUser(User user);
//    public List<Object> isUserRegistered(User user);
    public boolean isUserRegistered(User user);
}
