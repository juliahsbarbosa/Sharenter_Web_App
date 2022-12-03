package com.project.sharenter.repository;

import com.project.sharenter.model.Listing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {

    Page<Listing> findAll(Pageable pageable);

    Page<Listing> findByAddressContainingIgnoreCase(String searchedCounty,Pageable pageable);
//    Page<Listing> findByRentBetween(double min, int max, Pageable pageable);


}
