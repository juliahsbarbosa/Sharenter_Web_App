package com.project.sharenter.controller;

import com.project.sharenter.dto.ListingDto;
import com.project.sharenter.model.Inquiry;
import com.project.sharenter.model.Listing;
import com.project.sharenter.service.InquiryService;
import com.project.sharenter.service.ListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ListingController {
    @Autowired
    private ListingService listingService;


    @Value("${maps.api.key}")
    private String mapsApiKey;


    @Autowired
    InquiryService inquiryService;

    //GET method, display a specific listing details according to its id
    @GetMapping("/renter/listing-details/{id}")
    public String listingDetails(@PathVariable("id") long id, Model model) {
        Listing listing = listingService.getListingById(id);


        //Counts the number of inquiries by listing Id
        long count = inquiryService.countInquiriesByListing(id);

        model.addAttribute("listing", listing);
        model.addAttribute("count", count);
        model.addAttribute("inquiry", new Inquiry());

        return "renter/listing-details";
    }


    //GET method, displays the browse listings page with pagination, search and sorting functionalities
    @GetMapping("/renter/browse-listings")
    public String listingPaginated(Model model, @RequestParam(required = false) String searchedCounty,
                                   @RequestParam(defaultValue = "1") int page,
                                   @RequestParam(defaultValue = "3") int size,
                                   @RequestParam(defaultValue = "creationDate") String sortField,
                                   @RequestParam(defaultValue = "sortBy") String sortBy) {


        Page<Listing> listingPage;

        //If user doesn't search by county, display all listings
        if (searchedCounty == null) {
            listingPage = listingService.allListingsPaginated(page, size, sortField, sortBy);

            //Else display according to the searched county
        } else {
            listingPage = listingService.countySearchPaginated(searchedCounty, page, size, sortField, sortBy);
            model.addAttribute("searchedCounty", searchedCounty);
        }
        List<Listing> allListings = listingPage.getContent();

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", listingPage.getTotalPages());
        model.addAttribute("totalItems", listingPage.getTotalElements());
        model.addAttribute("pageSize", size);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("reverseSortBy", sortBy.equals("asc") ? "desc" : "asc");
        model.addAttribute("allListings", allListings);

        return "renter/browse-listings";
    }

    //GET method display the new listing form
    @GetMapping("/sharer/new-listing")
    public String newListing(Model model) {
        model.addAttribute("listing", new Listing());
        model.addAttribute("mapsApiKey", mapsApiKey);
        return "sharer/new-listing";
    }

    //POST method, saves the new listing in the database
    @PostMapping(value = "/sharer/new-listing")
    public String saveListing(@Valid @ModelAttribute("listing") ListingDto listingDto, BindingResult bindingResult) {

        //If there is any validation errors, display form again
        if (bindingResult.hasErrors()) {
            return "sharer/new-listing";
        }
        listingService.createListing(listingDto);
        return "redirect:/sharer/new-listing?success";
    }

    //GET method, displays the edit listing form according to the listing id
    @GetMapping("/sharer/edit-listing/{id}")
    public String editListing(@PathVariable(value = "id") long id, Model model) {
        Listing listing = listingService.getListingById(id);

        model.addAttribute("listing", listing);
        return "sharer/edit-listing";
    }

    //POST method, saves the edited listing to the database
    @PostMapping(value = "/sharer/edit-listing")
    public String updateListing(@Valid @ModelAttribute("listing") ListingDto listingDto,  BindingResult bindingResult) {

        //If there is any validation error, display form again
        if (bindingResult.hasErrors()) {
            return "sharer/edit-listing";
        }
        listingService.createListing(listingDto);
        return "redirect:/sharer/dashboard";
    }

    //GET method, deletes a listing according to its id
    @GetMapping("/sharer/delete-listing/{id}")
    public String deleteListing(@PathVariable(value = "id") long id) {

        // call delete listing method
        this.listingService.deleteListingById(id);
        return "redirect:/sharer/dashboard";
    }


}
