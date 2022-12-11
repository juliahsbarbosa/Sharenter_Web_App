package com.project.sharenter.repository;

import com.project.sharenter.model.Listing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {

    //Find listings by createdBy with pagination
    Page<Listing> findListingByCreatedBy(String createdByEmail, Pageable pageable);

    //Find all listings with pagination
    Page<Listing> findAll(Pageable pageable);

    //Find listing if the address contains the seached county, with pagination
    Page<Listing> findListingByAddressContainingIgnoreCase(String searchedCounty,Pageable pageable);

}
