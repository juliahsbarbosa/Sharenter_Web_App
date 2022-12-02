package com.project.sharenter.repository;

import com.project.sharenter.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {
    List<Listing> findByCountyContainingIgnoreCase(String county);

}
