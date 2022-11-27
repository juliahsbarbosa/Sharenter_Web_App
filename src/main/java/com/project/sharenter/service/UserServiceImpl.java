package com.project.sharenter.service;

import com.project.sharenter.dto.UserDto;
import com.project.sharenter.model.Role;
import com.project.sharenter.model.User;
import com.project.sharenter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @Override
    public void registerUser(UserDto userDto) {
        User user = new User();

        //Mapping UserDto to User
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        String encodedPassword = bCryptPasswordEncoder.encode(userDto.getPassword());
        user.setPassword(encodedPassword);

        //Mapping Enum role according to inputed value

        switch (userDto.getRole()) {
            case SHARER -> user.setRole(Role.SHARER);
            case RENTER -> user.setRole(Role.RENTER);
        }

        userRepository.save(user);
    }


    //
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                ()-> new UsernameNotFoundException(email));
    }
}
