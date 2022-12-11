package com.project.sharenter.controller;

import com.project.sharenter.model.Inquiry;
import com.project.sharenter.model.Listing;

import com.project.sharenter.service.InquiryService;
import com.project.sharenter.service.ListingService;
import com.project.sharenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class MainController {

    @Value("${maps.api.key}")
    private String mapsApiKey;


    @Autowired
    UserService userService;

    @Autowired
    ListingService listingService;

    @Autowired
    InquiryService inquiryService;


    //GET method, displays the homepage and the search form
    @GetMapping("/")
    public String home(Model model){

        Page<Listing> listingPage = listingService.allListingsPaginated(1, 3, "creationDate", "desc");
        List<Listing> recentListings = listingPage.getContent();
        model.addAttribute("recentListings", recentListings);
        return "index";
    }


    //HTTP GET Requests, displays the sharer's dashboard to the user who is currently logged in
    @GetMapping("/sharer/dashboard")
    public String sharerDashboard(Model model, Principal principal,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "5") int size,
                                  @RequestParam(defaultValue = "creationDate") String sortField,
                                  @RequestParam(defaultValue = "sortBy") String sortBy) {


        //Returns the email of the current logged in user
        String currentEmail = principal.getName();

        Page<Listing> findByEmail = listingService.getAllByUserEmail(currentEmail, page, size, sortField, sortBy);

        model.addAttribute("email", currentEmail);

        List<Listing> listingsByUser = findByEmail.getContent();

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", findByEmail.getTotalPages());
        model.addAttribute("totalItems", findByEmail.getTotalElements());
        model.addAttribute("pageSize", size);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("reverseSortBy", sortBy.equals("asc") ? "desc" : "asc");
        model.addAttribute("currentEmail", currentEmail);
        model.addAttribute("listingsByUser", listingsByUser);
        return "sharer/dashboard";
    }

    //HTTP GET Request, displays the renter dashboard to the user who is currently logged in
    @GetMapping("/renter/dashboard")
    public String renterDashboard(Model model, Principal principal,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "5") int size,
                                  @RequestParam(defaultValue = "creationDate") String sortField,
                                  @RequestParam(defaultValue = "sortBy") String sortBy) {

        //Returns the email of the current logged in user
        String currentEmail = principal.getName();

        Page<Inquiry> findByEmail = inquiryService.allInquiriesByCreatedBy(currentEmail, page, size, sortField, sortBy);

        model.addAttribute("email", currentEmail);

        List<Inquiry> inquiries = findByEmail.getContent();

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", findByEmail.getTotalPages());
        model.addAttribute("totalItems", findByEmail.getTotalElements());
        model.addAttribute("pageSize", size);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("reverseSortBy", sortBy.equals("asc") ? "desc" : "asc");
        model.addAttribute("currentEmail", currentEmail);
        model.addAttribute("inquiries", inquiries);

        return "renter/dashboard";
    }

    //HTTP GET Request, displays the about page
    @GetMapping("/about")
    public String about() {
        return "main/about";
    }

    //HTTP GET Request, displays the how page
    @GetMapping("/how")
    public String howItWorks() {
        return "main/how";
    }


}
