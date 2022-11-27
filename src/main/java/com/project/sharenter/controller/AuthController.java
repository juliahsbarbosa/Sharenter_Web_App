package com.project.sharenter.controller;


import com.project.sharenter.dto.UserDto;
import com.project.sharenter.model.User;
import com.project.sharenter.repository.UserRepository;
import com.project.sharenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class AuthController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(){
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return  "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(Model model, @Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult){

        Optional<User> existingUserEmail = userRepository.findByEmail(userDto.getEmail());
        if(existingUserEmail.isPresent()) {
            bindingResult.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("user", userDto );
            return  "auth/register";
        }

        userService.registerUser(userDto);
        return "redirect:register?success";
    }

}
