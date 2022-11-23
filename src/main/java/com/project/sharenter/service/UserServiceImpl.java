package com.project.sharenter.service;

import com.project.sharenter.model.Role;
import com.project.sharenter.model.User;
//import com.project.sharenter.model.RegisterForm;
import com.project.sharenter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @Override
    //@PathVariable("role") String role,
    public void registerUser( User user) {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
//        user.setRole(Role.valueOf(role.toUpperCase()));
        userRepository.save(user);
    }

//    public void registerUser( RegisterForm userDto) {
//        RegisterForm user = new RegisterForm();
//
//        user.setFirstName(userDto.getFirstName());
//        user.setLastName(userDto.getLastName());
//        user.setEmail(userDto.getEmail());
//
//        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
//
////        user.setRole(Role.valueOf(role.toUpperCase()));
//        userRepository.save(user);
//    }


    public boolean isUserRegistered(User user) {
        Optional<User> existingUserEmail = userRepository.findByEmail(user.getEmail());
        if(existingUserEmail.isPresent()){
            return true;
        }
        return false;
    }

    //
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                ()-> new UsernameNotFoundException(email));
    }
}
