//Attempt to implement the service for the Inquiry Entity


package com.project.sharenter.service;

import com.project.sharenter.dto.InquiryDto;
import com.project.sharenter.model.Inquiry;
import com.project.sharenter.model.Listing;
import com.project.sharenter.repository.InquiryRepository;
import com.project.sharenter.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InquiryServiceImpl implements InquiryService{
    @Autowired
    InquiryRepository inquiryRepository;
    @Autowired
    ListingService listingService;

    @Autowired
    ListingRepository listingRepository;

;

    @Override
    public void save(InquiryDto inquiryDto, Listing listing) {
        Inquiry inquiry = new Inquiry();

//        Listing listing = new Listing();
//
        //Trying to Set the OnetoMany relatioship
        inquiry.setListing(listing);

        inquiry.setName(inquiryDto.getName());
        inquiry.setEmail(inquiryDto.getEmail());
        inquiry.setPhone(inquiryDto.getPhone());
        inquiry.setInquiry(inquiryDto.getInquiry());

        inquiryRepository.save(inquiry);
    }


}
