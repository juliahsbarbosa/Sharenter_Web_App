package com.project.sharenter.controller;

import com.project.sharenter.model.Listing;
import com.project.sharenter.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Value("${maps.api.key}")
    private String mapsApiKey;

    @Autowired
    ListingRepository listingRepository;

    //Controller method to display the homepage and the search form
    @GetMapping("/")
    public String home(Model model, Listing listing){
        model.addAttribute("listings", listing);
        return "index";
    }

    @GetMapping("/renter/teste")
    public String test(Model model, Listing listing) {
        model.addAttribute("mapsApiKey", mapsApiKey);
        model.addAttribute("listing", listing);


        return "renter/teste";
    }

    @GetMapping("/sharer/dashboard")
    public String sharerDashboard() {
        return "sharer/dashboard";
    }

    @GetMapping("/renter/dashboard")
    public String renterDashboard() {
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
