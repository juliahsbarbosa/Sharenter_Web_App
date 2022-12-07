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

//    Long countByCount();

    //Find listings by createdBy
    Page<Listing> findListingByCreatedBy(String createdByEmail, Pageable pageable);

    //Find all listings
    Page<Listing> findAll(Pageable pageable);

    //Find listing if the address contains the seached county
    Page<Listing> findListingByAddressContainingIgnoreCase(String searchedCounty,Pageable pageable);

//    Page<Listing> findByRentBetween(int min, int max, Pageable pageable);


}
