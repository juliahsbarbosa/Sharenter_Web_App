package com.project.sharenter.service;

import com.project.sharenter.dto.ListingDto;
import com.project.sharenter.model.Listing;
import com.project.sharenter.repository.ListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListingServiceImpl implements ListingService {
    private final ListingRepository listingRepository;

    @Override
    public void createListing(ListingDto listingDto) {
        Listing listing = new Listing();

        //Mapping ListingDto to listing
        listing.setTitle(listingDto.getTitle());
        listing.setRent(listingDto.getRent());
        listing.setLandlordOccupied(listingDto.isLandlordOccupied());
        listing.setBillsIncluded(listingDto.isBillsIncluded());
        listing.setParking(listingDto.isParking());
        listing.setPetFriendly(listingDto.isPetFriendly());
        listing.setPrivateBathroom(listingDto.isPrivateBathroom());
        listing.setSuitableForCouples(listingDto.isSuitableForCouples());
        listing.setHousemates(listingDto.getHousemates());
        listingRepository.save(listing);

    }



    @Override
    public List< Listing > getAllListings() {
        return listingRepository.findAll();
    }

    @Override
    public Listing getListingById(Long id) {
        Optional<Listing> optional = listingRepository.findById(id);
        Listing listing = null;
        if (optional.isPresent()) {
            listing = optional.get();
        } else {
            throw new RuntimeException(" Listing not found for id : " + id);
        }
        return listing;
    }

    @Override
    public void deleteListingById(Long id) {
        this.listingRepository.deleteById(id);
    }

    @Override
    public Page<Listing> findPaginated(int pageNo, int pageSize, String sortField, String sortBy) {
        Sort sort = sortBy.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.listingRepository.findAll(pageable);
    }
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
