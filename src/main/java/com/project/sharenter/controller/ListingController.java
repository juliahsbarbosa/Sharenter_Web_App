package com.project.sharenter.controller;

import com.project.sharenter.dto.ListingDto;
import com.project.sharenter.model.Listing;
import com.project.sharenter.service.ListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ListingController {
    @Autowired
    private ListingService listingService;

    @Value("${maps.api.key}")
    private String mapsApiKey;


    //Mapping the indidual listing details page with path variable by id
    @GetMapping("/renter/listing-details/{id}")
    public String listingDetails(@PathVariable("id") long id, Model model) {
        Listing listing = listingService.getListingById(id);
        model.addAttribute("listing", listing);
        model.addAttribute("mapsApiKey", mapsApiKey);
        return "renter/listing-details";
    }


    //Mapping the browse-listings page with pagination, search and filtering options
    @GetMapping("/renter/browse-listings")
    public String listingPaginated(Model model, @RequestParam(required = false) String searchedCounty,
                                   @RequestParam(defaultValue = "1") int page,
                                   @RequestParam(defaultValue = "3") int size,
                                   @RequestParam(defaultValue = "creationDate") String sortField,
                                   @RequestParam(defaultValue = "sortBy") String sortBy) {


        Page<Listing> listingPage;
        if (searchedCounty == null) {
            listingPage = listingService.allListingsPaginated(page, size, sortField, sortBy);

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

    //Mapping the create new listing
    @GetMapping("/sharer/new-listing")
    public String newListing(Model model) {
        //Model attribute to bind form data
        model.addAttribute("listing", new Listing());
        model.addAttribute("mapsApiKey", mapsApiKey);
        return "sharer/new-listing";
    }

    //Post mapping for the new listing
    @PostMapping(value = "/sharer/new-listing")
    public String saveListing(Model model, @Valid @ModelAttribute("listing") ListingDto listingDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "sharer/new-listing";
        }
        listingService.createListing(listingDto);
        return "redirect:/sharer/new-listing?success";
    }

    //Mapping for the editing a listing by path variable id
    @GetMapping("/sharer/edit-listing/{id}")
    public String editListing(@PathVariable(value = "id") long id, Model model) {
        Listing listing = listingService.getListingById(id);
        model.addAttribute("listing", listing);
        model.addAttribute("mapsApiKey", mapsApiKey);
        return "sharer/edit-listing";
    }

    @PostMapping(value = "/sharer/edit-listing")
    public String updateListing(Model model, @Valid @ModelAttribute("listing") ListingDto listingDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "sharer/edit-listing";
        }
        listingService.createListing(listingDto);
        return "redirect:/sharer/dashboard";
    }

    //Mapping for the deleting a listing by path variable id
    @GetMapping("/sharer/delete-listing/{id}")
    public String deleteListing(@PathVariable(value = "id") long id) {

        // call delete listing method
        this.listingService.deleteListingById(id);
        return "redirect:/sharer/dashboard";
    }

    //Returns form results as JSON in order to create markers in the Google Maps Javascript API
    @GetMapping("/api/listings")
    @ResponseBody
    public ResponseEntity<List<Listing>> getAllUsers() {
        final List<Listing> listings = listingService.getAllListings().stream().collect(Collectors.toList());
        return ResponseEntity.ok(listings);
    }

}
