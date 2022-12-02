package com.project.sharenter.controller;

import com.project.sharenter.dto.ListingDto;
import com.project.sharenter.model.Listing;
import com.project.sharenter.service.ListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ListingController {
    @Autowired
    private ListingService listingService;

    @Value("${maps.api.key}")
    private String mapsApiKey;

    //Calling the pagination method and mapping browse listings
    @GetMapping("renter/browse-listings")
    public String browseListings(Model model) {
        return findPage(1, "title", "asc", model);

    }

    //Mapping the indidual listing details page with path variable by id
    @GetMapping("/renter/listing-details/{id}")
    public String listingDetails(@PathVariable("id") long id, Model model) {
        Listing listing = listingService.getListingById(id);
        model.addAttribute("listing", listing);
        model.addAttribute("mapsApiKey", mapsApiKey);


        return "renter/listing-details";
    }


    //Mapping the Browse listings with pagination
    @GetMapping("/renter/browse-listings/{pageNo}")
    public String findPage(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortBy") String sortBy,
                                Model model) {
        int pageSize = 3;

        Page<Listing> listingPage = listingService.findPaginated(pageNo, pageSize, sortField, sortBy);
        List<Listing> allListings = listingPage.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", listingPage.getTotalPages());
        model.addAttribute("totalItems", listingPage.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("reverseSortBy", sortBy.equals("asc") ? "desc" : "asc");
        model.addAttribute("allListings", allListings);
        model.addAttribute("mapsApiKey", mapsApiKey);

        return "renter/browse-listings";
    }


    //Mapping the create new listing
    @GetMapping("/sharer/new-listing")
    public String newListing(Model model) {
        //Model attribute to bind form data
        model.addAttribute("listing", new Listing());
        model.addAttribute("mapsApiKey", mapsApiKey);
        return "sharer/new-listing";
    }

    //Post mapping for the new listing
    @PostMapping("/sharer/new-listing")
    public String saveNewListing(Model model, @Valid @ModelAttribute("listing") ListingDto listingDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "sharer/new-listing";
        }
        listingService.createListing(listingDto);
        return "redirect:/sharer/new-listing?success";
    }

    @GetMapping("/sharer/edit-listing/{id}")
    public String editListing(@PathVariable(value = "id") long id, Model model) {
        Listing listing = listingService.getListingById(id);
        model.addAttribute("listing", listing);
        return "sharer/edit-listing";
    }

    //Mapping to delete a listing
    @GetMapping("/sharer/delete-listing/{id}")
    public String deleteListing(@PathVariable(value = "id") long id) {

        // call delete listing method
        this.listingService.deleteListingById(id);
        return "redirect:/sharer/dashboard";
    }

    //Returns form results as JSON
    @GetMapping("/api/listings")
    @ResponseBody
    public ResponseEntity<List<Listing>> getAllUsers() {
        final List<Listing> listings = listingService.getAllListings().stream().collect(Collectors.toList());
        return ResponseEntity.ok(listings);
    }

}

//////
//////    @GetMapping("/api/listings")
//////    @ResponseBody
//////    public ResponseEntity<List<Listing>> getAllUsers() {
//////        final List<Listing> listings = listingService.getAll().stream().filter(e -> e.getGeocoding() != null).collect(Collectors.toList());
//////        return ResponseEntity.ok(listings);
//////    }
////
////    private final String UPLOAD_DIR = "./uploads/";
////
////
////    @PostMapping("/upload")
////    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
////
////        // check if file is empty
////        if (file.isEmpty()) {
////            attributes.addFlashAttribute("message", "Please select a file to upload.");
////            return "redirect:/";
////        }
////
////        // normalize the file path
////        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
////
////        // save the file on the local file system
////        try {
////            Path path = Paths.get(UPLOAD_DIR + fileName);
////            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////
////        // return success response
////        attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');
////
////        return "redirect:/";
////    }
////}

