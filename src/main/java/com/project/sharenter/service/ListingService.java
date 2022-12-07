package com.project.sharenter.service;


import com.project.sharenter.dto.ListingDto;
import com.project.sharenter.model.Listing;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ListingService {

    public List<Listing> getAllListings();

    public void createListing(ListingDto listingDto);

    Listing getListingById(long id);

    void deleteListingById(long id);

    Page<Listing> allListingsPaginated(int pageNo, int pageSize, String sortField, String sortBy);

    Page<Listing> countySearchPaginated(String searchedCounty, int pageNo, int pageSize, String sortField, String sortBy);

    Page<Listing> getAllByUserEmail(String email, int pageNo, int pageSize, String sortField, String sortBy);

}