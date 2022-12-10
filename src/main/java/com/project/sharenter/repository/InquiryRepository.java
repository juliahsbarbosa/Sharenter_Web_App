//Attempt to create a Repository for the Inquiry Entity

package com.project.sharenter.repository;

import com.project.sharenter.model.Inquiry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry,Long> {
    Page<Inquiry> findInquiryByCreatedBy(String createdByEmail, Pageable pageable);

    Inquiry findInquiryByListingId(long id);

    Inquiry findInquiryByListingIdAndCreatedBy(long id, String createdByEmail);

    long countByListingId(long id);
}