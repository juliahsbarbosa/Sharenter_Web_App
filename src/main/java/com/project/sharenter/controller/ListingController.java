//package com.project.sharenter.controller;
//
////import com.project.sharenter.dto.ListingDto;
//import com.project.sharenter.model.Listing;
////import com.project.sharenter.service.ListingService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Controller
//@RequestMapping("/")
//@RequiredArgsConstructor
//public class ListingController {
//
//    private final ListingService listingService;
//    @Value("${maps.api.key}")
//    private String mapsApiKey;
//
//    @GetMapping
//    public ModelAndView viewAllListings() {
//        final List<Listing> listings = listingService.getAll();
//        final ModelAndView modelAndView = new ModelAndView("listings");
//        modelAndView.addObject("listings", listings);
//        modelAndView.addObject("gmapsApiKey", mapsApiKey);
//        modelAndView.addObject("listing", new Listing());
//        return modelAndView;
//    }
//
//    @PostMapping
//    public ModelAndView createListing(@ModelAttribute ListingDto listingDto) {
//        listingService.create(listingDto);
//        return new ModelAndView("redirect:/");
//    }
//
//    @GetMapping("/api/listings")
//    @ResponseBody
//    public ResponseEntity<List<Listing>> getAllUsers() {
//        final List<Listing> listings = listingService.getAll().stream().filter(e -> e.getGeocoding() != null).collect(Collectors.toList());
//        return ResponseEntity.ok(listings);
//    }
//}
