//Attempt to create a Service for the Inquiry Entity


package com.project.sharenter.service;

import com.project.sharenter.dto.InquiryDto;
import com.project.sharenter.model.Inquiry;
import com.project.sharenter.model.Listing;
import org.springframework.data.domain.Page;

public interface InquiryService {

    void save(InquiryDto inquiryDto, Listing listing);

    //Renter Dashboard
    Page<Inquiry> allInquiriesByCreatedBy(String email, int pageNo, int pageSize, String sortField, String sortBy);

    //Sharer Dashboard
//    Page<Listing> allInquiriesByCreatedByAndListingId(String email, Long id, int pageNo, int pageSize, String sortField, String sortBy);

    //Count by Listing Id
    long countInquiriesByListing (Long id);



}
