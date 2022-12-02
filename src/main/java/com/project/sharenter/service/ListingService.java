package com.project.sharenter.service;


import com.project.sharenter.dto.ListingDto;
import com.project.sharenter.model.Listing;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ListingService {

//    void createListing(ListingDto listingDto, MultipartFile multipartFile) throws Exception;

    public List< Listing > getAllListings();

    public void createListing(ListingDto listingDto);

    //create method to search employee by id
    Listing getListingById(long id);

    //create method to delete an employee
    void deleteListingById(long id);

    //Pagination using Spring Data JPA and sorting
    Page<Listing> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}

//    public void deleteListing(Listing listing);
//
//    public void editListing(ListingDto listingDto);



//    public void deleteApartment(Listing listing) {
//        Optional<Listing> listingOptional = listingRepository.findListingByByAddress(String.valueOf(listing.getAddress()));
//
//        if(listingOptional.isPresent()) {
//            listingRepository.delete(listing);
//        } else {
//            throw new IllegalStateException("Teste");
//        }
//    }

//    @Transactional
//    public void updateApartment(String address, String updatedAddress) {
//        Listing listing = listingRepository.findListingByByAddress(address).orElseThrow(() -> new IllegalStateException("This apartment does not seem to exist!"));
//
//        if (updatedAddress != null && updatedAddress.length() > 0) {
//            listing.setAddress(updatedAddress);
//        }
//
//    }

//    public void createListing(Listing listing);
//    public List<Listing> getAll();
//    public void editListing(Listing listing);
//    public void deleteListing(Listing listing);
//
//    //	public List<Flat> addFlat(Flat flat);
//    //
//    //	public List<Flat> updateFlat(Integer flatId, Double cost) throws FlatNotFoundException;
//    //
//    //	public List<Flat> deleteFlat(Integer flatId) throws FlatNotFoundException;
//    //
//    //	public Flat viewFlat(Integer flatId) throws FlatNotFoundException;
//    //
//    //	public List<Flat> viewAllFlat();
//    //
//    //	public List<Flat> findByCostAndAvailability(Double cost,String availability);
//}