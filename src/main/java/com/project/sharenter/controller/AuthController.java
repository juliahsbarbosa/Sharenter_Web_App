package com.project.sharenter.controller;


import com.project.sharenter.model.User;
import com.project.sharenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AuthController {
    @Autowired
    UserService userService;

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
    public String registerUser(Model model, @Valid User user, BindingResult bindingResult){

        if(userService.isUserRegistered(user)){
//            model.addAttribute("errorMessage", "Email already registered");
            bindingResult.rejectValue("email", null, "There is already an account registered with that email");

//            return "auth/register";
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("user", user);
            return  "auth/register";
        }

        userService.registerUser(user);
        model.addAttribute("successMessage", "User registered successfully!");

        return "redirect:register?success";
    }

}
