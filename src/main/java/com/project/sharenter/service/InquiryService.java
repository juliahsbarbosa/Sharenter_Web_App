//Attempt to create a Service for the Inquiry Entity


package com.project.sharenter.service;

import com.project.sharenter.dto.InquiryDto;
import com.project.sharenter.model.Inquiry;
import com.project.sharenter.model.Listing;

public interface InquiryService {

    void save(InquiryDto inquiryDto, Listing listing);
}
