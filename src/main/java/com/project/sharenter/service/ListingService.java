package com.project.sharenter.service;


import com.project.sharenter.dto.ListingDto;
import com.project.sharenter.model.Listing;
import org.springframework.data.domain.Page;

public interface ListingService {

    //Creates a listing
    void createListing(ListingDto listingDto);

    //Get listing by its id
    Listing getListingById(long id);


    //Deletes a listing by its id
    void deleteListingById(long id);

    //Returns all listings paginated
    Page<Listing> allListingsPaginated(int pageNo, int pageSize, String sortField, String sortBy);

    //Returns all listings that contain the term searched in its address, paginated
    Page<Listing> countySearchPaginated(String searchedCounty, int pageNo, int pageSize, String sortField, String sortBy);

    //Returns all listings created by a user(identified by their email), paginated
    Page<Listing> getAllByUserEmail(String email, int pageNo, int pageSize, String sortField, String sortBy);



}