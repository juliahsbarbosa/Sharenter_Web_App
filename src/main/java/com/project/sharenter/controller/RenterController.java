package com.project.sharenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RenterController {

//    @RequestMapping(value = {"/renter/dashboard"}, method = RequestMethod.GET)
    @GetMapping("/renter/dashboard")
    public String renterDashboard(){
        return "renter/dashboard";
    }

}
