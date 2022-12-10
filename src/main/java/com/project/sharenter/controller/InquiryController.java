//package com.project.sharenter.controller;
//
//import com.project.sharenter.dto.InquiryDto;
//import com.project.sharenter.model.Listing;
//import com.project.sharenter.repository.InquiryRepository;
//import com.project.sharenter.service.ListingService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.validation.Valid;
//import java.security.Principal;
//
//@Controller
//public class InquiryController {
//
//    @Autowired
//    InquiryService inquiryService;
//
//    @PostMapping("/renter/message")
//    public String registerUser(Model model, @Valid @ModelAttribute("inquiry") InquiryDto inquiryDto, BindingResult bindingResult){
//
//        if(bindingResult.hasErrors()){
//            model.addAttribute("inquiry", inquiryDto );
//            return  "renter/listing-details";
//        }
//
//        inquiryService.saveInquiry(inquiryDto);
//        return "redirect:listing-details{id}?success";
//    }
//
//    @Autowired
//    ListingService listingService;
//
//    @Autowired
//    InquiryRepository inquiryRepository;
//    @PostMapping("/listing/{id}/addToFavorites")
//    public String addToFavorites(
//            @PathVariable long id, @RequestParam(name = "calledFrom", defaultValue = "catalog") String calledFrom,
//            Principal principal
//    ) {
//        Listing listing = listingService.getListingById(id);
//        userService.addToFavorites(currentUser, item);
//
//        if (calledFrom.equals("home")) return "redirect:/";
//        if (calledFrom.equals("item")) return "redirect:/catalog/item/%s".formatted(code);
//        return "redirect:/catalog/%s".formatted(item.getItemType().name().concat("s").toLowerCase());
//}
