//Attempt to implement the service for the Inquiry Entity


package com.project.sharenter.service;

import com.project.sharenter.dto.InquiryDto;
import com.project.sharenter.model.Inquiry;
import com.project.sharenter.model.Listing;
import com.project.sharenter.repository.InquiryRepository;
import com.project.sharenter.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class InquiryServiceImpl implements InquiryService{
    @Autowired
    InquiryRepository inquiryRepository;
    @Autowired
    ListingService listingService;

    @Autowired
    ListingRepository listingRepository;

    @Override
    public void save(InquiryDto inquiryDto, Listing listing) {
        Inquiry inquiry = new Inquiry();

        //Setting the Many To One relationship
        inquiry.setListing(listing);

        inquiry.setName(inquiryDto.getName());
        inquiry.setEmail(inquiryDto.getEmail());
        inquiry.setPhone(inquiryDto.getPhone());
        inquiry.setInquiry(inquiryDto.getInquiry());
        inquiryRepository.save(inquiry);
    }

    @Override
    public Page<Inquiry> allInquiriesByCreatedBy(String email, int pageNo, int pageSize, String sortField, String sortBy) {
        Sort sort = sortBy.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.inquiryRepository.findInquiryByCreatedBy(email, pageable);
    }

    @Override
    public long countInquiriesByListing(Long id) {
        return this.inquiryRepository.countByListingId(id);
    }
}
