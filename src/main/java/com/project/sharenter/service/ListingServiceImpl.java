package com.project.sharenter.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.project.sharenter.dto.ListingDto;
import com.project.sharenter.model.*;
import com.project.sharenter.repository.ListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ListingServiceImpl implements ListingService {
    private final ListingRepository listingRepository;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Value("${maps.api.key}")
    private String mapsApiKey;

    @Value("${walkscore.api.key}")
    private String walkApiKey;


    @Override
    public void createListing(ListingDto listingDto){
        Listing listing = new Listing();

        //Saving the new listing in the database
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

        switch (listingDto.getRoomType()) {
            case Single -> listing.setRoomType(RoomType.Single);
            case Double -> listing.setRoomType(RoomType.Double);
            case Twin -> listing.setRoomType(RoomType.Twin);
            case Triple -> listing.setRoomType(RoomType.Triple);
        }
//        String mapsUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=" +
//                listingDto.getAddress() + "&key=" + mapsApiKey;

        //Google Maps Geocoding API set up
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(mapsApiKey)
                .maxRetries(2)
                .connectTimeout(10L, TimeUnit.SECONDS)
                .build();

        GeocodingResult[] results;

        try {
            results = GeocodingApi.geocode(context, listingDto.getAddress()).await();
            listing.setLat(results[0].geometry.location.lat);
            listing.setLng(results[0].geometry.location.lng);
            listing.setAddress(results[0].formattedAddress);
            listing.setCounty(results[0].addressComponents[4].longName);
        } catch (Exception e) {
            listing.setLat(0);
            listing.setLng(0);
            listing.setAddress(null);
        }

        //Walkscore API set up
        String walkUrl = "https://api.walkscore.com/score?format=json&address=" +
                listing.getAddress() +"&lat=" + listing.getLat() + "&lon=" + listing.getLng() + "&wsapikey=" + walkApiKey;

        RestTemplate restTemplate = new RestTemplate();
        WalkScore walkScore = restTemplate.getForObject(walkUrl, WalkScore.class);

        //Saving Walkscore results on Listing database
        listing.setWalkscore(walkScore.getWalkscore());
        listing.setWalkscoreDescription(walkScore.getDescription());

        listingRepository.save(listing);
    }


    //Display all listings
    @Override
    public List< Listing > getAllListings() {
        final List<Listing> listings = listingRepository.findAll();

        return listings;
    }


    //Get Listing by id
    @Override
    public Listing getListingById(long id) {
        Optional<Listing> optional = listingRepository.findById(id);
        Listing listing = null;
        if (optional.isPresent()) {
            listing = optional.get();
        } else {
            throw new RuntimeException(" Listing not found for id : " + id);
        }
        return listing;
    }


    //Delete Listing by id
    @Override
    public void deleteListingById(long id) {
        this.listingRepository.deleteById(id);
    }


    //Implements pagination
    @Override
    public Page<Listing> findPaginated(int pageNo, int pageSize, String sortField, String sortBy) {
        Sort sort = sortBy.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();


        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.listingRepository.findAll(pageable);
    }

}
