package com.project.sharenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/sharer/dashboard")
    public String sharerDashboard(){
        return "sharer/dashboard";
    }

    @GetMapping("/renter/dashboard")
    public String renterDashboard(){
        return "renter/dashboard";
    }

//    @GetMapping("/access-denied")
//    public String denied() {
//        return "access-denied";
//    }
//
//    @GetMapping("/contact")
//    public String contact() {
//        return "contact";
//    }
//
//    @GetMapping("/about")
//    public String about() {
//        return "about";
//    }


}
