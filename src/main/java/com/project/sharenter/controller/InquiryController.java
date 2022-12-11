/*
 * Controller was going to be used to map out the Inquiry.
 */

package com.project.sharenter.controller;

import com.project.sharenter.dto.InquiryDto;
import com.project.sharenter.model.Listing;
import com.project.sharenter.service.InquiryService;
import com.project.sharenter.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class InquiryController {

    @Autowired
    InquiryService inquiryService;

    @Autowired
    ListingService listingService;


    //POST method, saves the contact inquiry by listing id
    @PostMapping("/renter/listing-details/{id}")
    public String sendInquiry(Model model,@ModelAttribute("listing") Listing listing, @Valid @ModelAttribute("inquiry") InquiryDto inquiryDto, BindingResult bindingResult){

        //If there is any validation errors, display form again
        if(bindingResult.hasErrors()){
            model.addAttribute("inquiry", inquiryDto );
            model.addAttribute("listing", listing );

            return  "renter/listing-details";
        }

        model.addAttribute("inquiry", inquiryDto);
        inquiryService.save(inquiryDto, listing);

        return "redirect:{id}?success";
    }

}
