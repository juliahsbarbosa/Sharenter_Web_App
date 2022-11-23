//package com.project.sharenter.service;
//
//import com.project.sharenter.dto.ListingDto;
//import com.project.sharenter.model.Address;
//import com.project.sharenter.model.Listing;
//import com.project.sharenter.repository.ListingRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//class ListingServiceImpl implements ListingService {
//    private final ListingRepository listingRepository;
//    private final GeocodingService geocodingService;
//
//    @Transactional
//    public void create(ListingDto form) {
//        final Listing listing = new Listing(form.getTitle(), form.getRent(),
//                new Address(form.getStreet(), form.getCity(), form.getCounty()));
//        listingRepository.save(listing);
//    }
//
//    @Override
//    @Transactional
//    public List<Listing> getAll() {
//        final List<Listing> listings = listingRepository.findAll();
//        listings.forEach(this::computeAddress);
//        return listings;
//    }
//
//    private void computeAddress(Listing listing) {
//        if (listing.getAddress() != null && listing.getGeocoding() == null && !listing.isGeoProcessed()) {
//            geocodingService.computeGeoLocation(listing.getAddress().toString())
//                    .ifPresent(listing::setGeocoding);
//            listing.setGeoProcessed(true);
//        }
//    }
//}