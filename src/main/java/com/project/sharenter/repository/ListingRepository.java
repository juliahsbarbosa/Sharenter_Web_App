package com.project.sharenter.repository;

import com.project.sharenter.model.Listing;
import com.project.sharenter.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {

    //Find listings by createdBy with pagination
    Page<Listing> findListingByCreatedBy(String createdByEmail, Pageable pageable);

    //Find all listings with pagination
    Page<Listing> findAll(Pageable pageable);

    //Find listing if the address contains the seached county, with pagination
    Page<Listing> findListingByAddressContainingIgnoreCase(String searchedCounty,Pageable pageable);

}
