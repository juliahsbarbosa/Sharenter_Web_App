package com.project.sharenter.service;

import com.project.sharenter.dto.ListingDto;
import com.project.sharenter.dto.UserDto;
import com.project.sharenter.model.Address;
import com.project.sharenter.model.Listing;
import com.project.sharenter.model.Role;
import com.project.sharenter.model.User;
import com.project.sharenter.repository.ListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListingServiceImpl implements ListingService {
    private final ListingRepository listingRepository;

    @Override
    public void createListing(ListingDto userDto) {
        Listing user = new Listing();

        //Mapping UserDto to User
        user.setTitle(userDto.getTitle());
        user.setRent(userDto.getRent());
        user.setLandlordOccupied(userDto.isLandlordOccupied());
        listingRepository.save(user);
    }

//    @Transactional
//    public void create(ListingDto form) {
//        final Listing listing = new Listing(form.getTitle(), form.getRent(),
//                new Address(form.getStreet(), form.getCity(), form.getCounty()));
//        listingRepository.save(listing);
//    }

//    @Override
//    @Transactional
//    public List<Listing> getAll() {
//        final List<Listing> listings = listingRepository.findAll();
//        listings.forEach(this::computeAddress);
//        return listings;
//    }

//    private void computeAddress(Listing listing) {
//        if (listing.getAddress() != null && listing.getGeocoding() == null && !listing.isGeoProcessed()) {
//            geocodingService.computeGeoLocation(listing.getAddress().toString())
//                    .ifPresent(listing::setGeocoding);
//            listing.setGeoProcessed(true);
//        }
//    }
}