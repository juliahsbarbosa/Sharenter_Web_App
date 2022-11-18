package com.project.sharenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListingsController {

    @GetMapping("/listings/listing-details")
    public String listingDetails() {
        return "listings/listing-details";
    }

    @GetMapping("/listings")
    public String listing() {
        return "listings/listings";
    }
}
