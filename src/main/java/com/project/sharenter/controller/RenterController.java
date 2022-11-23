package com.project.sharenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/renter")
public class RenterController {

    @GetMapping("/dashboard")
    public String renterDashboard(){
        return "renter/dashboard";
    }
    @GetMapping("/browse-listings")
    public String listingDetails() {
        return "renter/browse-listings";
    }

    @GetMapping("/listing-details")
    public String listing() {
        return "renter/listing-detail";
    }

}
