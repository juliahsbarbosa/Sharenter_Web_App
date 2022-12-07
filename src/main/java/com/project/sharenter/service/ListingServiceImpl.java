package com.project.sharenter.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.project.sharenter.dto.ListingDto;
import com.project.sharenter.model.*;
import com.project.sharenter.repository.ListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ListingServiceImpl implements ListingService {

    @Autowired
    private ListingRepository listingRepository;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Value("${maps.api.key}")
    private String mapsApiKey;

    @Value("${walkscore.api.key}")
    private String walkApiKey;


    //Method creates a listing
    @Override
    public void createListing(ListingDto listingDto){
        Listing listing = new Listing();

        //Handles the update
        if (listingDto.getId() != null){
            listing.setId(listingDto.getId());
        }

        //Map ListingDto to entity Listing, saving the record in the database
        listing.setTitle(listingDto.getTitle());
        listing.setRent(listingDto.getRent());
        listing.setLandlordOccupied(listingDto.isLandlordOccupied());
        listing.setBillsIncluded(listingDto.isBillsIncluded());
        listing.setParking(listingDto.isParking());
        listing.setPetFriendly(listingDto.isPetFriendly());
        listing.setPrivateBathroom(listingDto.isPrivateBathroom());
        listing.setSuitableForCouples(listingDto.isSuitableForCouples());
        listing.setNumHousemates(listingDto.getNumHousemates());
        listing.setImageUrl(listingDto.getImageUrl());

        //Role is set according to the specific Role Enum
        switch (listingDto.getRoomType()) {
            case Single -> listing.setRoomType(RoomType.Single);
            case Double -> listing.setRoomType(RoomType.Double);
            case Twin -> listing.setRoomType(RoomType.Twin);
            case Triple -> listing.setRoomType(RoomType.Triple);
        }

        //Google Maps Geocoding API Set up
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(mapsApiKey)
                .maxRetries(2)
                .connectTimeout(10L, TimeUnit.SECONDS)
                .build();

        GeocodingResult[] results;

        //The listingDto address is inputted based on the Google Maps Javascript API Autocomplete query
        //The listingDto address is used to get the geocoding results and these are saved in the Listing Entity
        try {
            results = GeocodingApi.geocode(context, listingDto.getAddress()).await();
            listing.setAddress(results[0].formattedAddress);
            listing.setLat(results[0].geometry.location.lat);
            listing.setLng(results[0].geometry.location.lng);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        //Walkscore API set up
        try {
            String walkUrl = "https://api.walkscore.com/score?format=json&address=" +
                    listing.getAddress() + "&lat=" + listing.getLat() + "&lon=" + listing.getLng() + "&wsapikey=" + walkApiKey;

            RestTemplate restTemplate = new RestTemplate();
            WalkScore walkScore = restTemplate.getForObject(walkUrl, WalkScore.class);

            //Saving Walkscore results on the Listing database
            listing.setWalkscore(walkScore.getWalkscore());
            listing.setWalkscoreDescription(walkScore.getDescription());
        }catch (Exception e){
            e.printStackTrace(System.out);
        }
        listingRepository.save(listing);
    }

    //Returns a list with all the listings
    @Override
    public List<Listing> getAllListings() {
        final List<Listing> listings = listingRepository.findAll();
        return listings;
    }


    //Returns a listing based on its id
    @Override
    public Listing getListingById(long id) {
        Optional<Listing> optional = listingRepository.findById(id);
        Listing listing = null;
        if (optional.isPresent()) {
            listing = optional.get();
        } else {
            throw new RuntimeException("Listing not found for id : " + id);
        }
        return listing;
    }


    //Delete a Listing based on its id
    @Override
    public void deleteListingById(long id) {
        this.listingRepository.deleteById(id);
    }


    //Implements pagination and sorting for all the listings
    @Override
    public Page<Listing> allListingsPaginated(int pageNo, int pageSize, String sortField, String sortBy) {
        Sort sort = sortBy.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return this.listingRepository.findAll(pageable);
    }


    //Implements pagination and sorting for listings that match the county searched
    @Override
    public Page<Listing> countySearchPaginated(String searchedCounty, int pageNo, int pageSize, String sortField, String sortBy) {
        Sort sort = sortBy.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.listingRepository.findListingByAddressContainingIgnoreCase(searchedCounty, pageable);

    }

    //Implements pagination and sorting on listings of the same user
    public Page<Listing> getAllByUserEmail(String email, int pageNo, int pageSize, String sortField, String sortBy){
        Sort sort = sortBy.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.listingRepository.findListingByCreatedBy(email, pageable);
    }

}
