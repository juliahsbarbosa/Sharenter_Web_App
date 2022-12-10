package com.project.sharenter.controller;

import com.project.sharenter.model.Listing;
import com.project.sharenter.model.User;
import com.project.sharenter.repository.ListingRepository;
import com.project.sharenter.service.ListingService;
import com.project.sharenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Value("${maps.api.key}")
    private String mapsApiKey;


    @Autowired
    UserService userService;

    @Autowired
    ListingService listingService;


    //Controller method to display the homepage and the search form
    @GetMapping("/")
    public String home(Model model){

        Page<Listing> listingPage = listingService.allListingsPaginated(1, 3, "creationDate", "desc");
        List<Listing> recentListings = listingPage.getContent();
        model.addAttribute("recentListings", recentListings);
        return "index";
    }

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

    @GetMapping("/renter/dashboard")
    public String renterDashboard(Model model, Principal principal) {
        String currentEmail = principal.getName();
        model.addAttribute("currentEmail", currentEmail);


        return "renter/dashboard";
    }

    @GetMapping("/about")
    public String aboutUs() {
        return "main/about";
    }

    @GetMapping("/how")
    public String howItWorks() {
        return "main/how";
    }




}
