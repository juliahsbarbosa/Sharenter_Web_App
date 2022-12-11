package com.project.sharenter.repository;

import com.project.sharenter.model.Inquiry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry,Long> {

    //Find Inquiries by the email of the user who created
    Page<Inquiry> findInquiryByCreatedBy(String createdByEmail, Pageable pageable);


    //Count the inquiries by listing id, to display how many inquiries the listing has
    long countByListingId(long id);
}