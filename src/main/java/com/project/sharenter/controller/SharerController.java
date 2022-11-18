package com.project.sharenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SharerController {

//    @RequestMapping(value = {"sharer/dashboard"}, method = RequestMethod.GET)
    @GetMapping("/sharer/dashboard")
    public String sharerDashboard(){
        return "sharer/dashboard";
    }


    @GetMapping("/sharer/edit-listing")
    public String editListing(){
        return "sharer/edit-listing";
    }

    @GetMapping("/sharer/new-listing")
    public String newListing(){
        return "sharer/new-listing";
    }
}
