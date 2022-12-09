//package com.project.sharenter.service;
//
//import com.project.sharenter.model.Inquiry;
//import com.project.sharenter.repository.ContactRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ContactServiceImpl implements ContactService  {
//
//    @Autowired
//    ContactRepository contactRepository;
//
//    public int sentContactCountByUser() {
//        int count = contactRepository.countAllByCreatedBy();
//        return count;
//    }
//
//    public int receivedContactCountByListing() {
//        int count = contactRepository.countAllByListingId();
//        return count;
//    }
//
//    public int receivedContactCountByListingAndByUser() {
//        int count = contactRepository.countAllByCreatedByaAndListingId();
//        return count;
//    }
//
//
//}