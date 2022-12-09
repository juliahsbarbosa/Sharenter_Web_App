//package com.project.sharenter.repository;
//
//import com.project.sharenter.model.Inquiry;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//
//public interface ContactRepository extends JpaRepository<Inquiry, Long> {
//
//    List<Inquiry> findAllByListingIdAndCreatedBy();
//
//    List<Inquiry> findAllByListingId();
//
//    List<Inquiry> findAllByCreatedBy();
//
//
//    int countAllByListingId();
//
//    int countAllByCreatedBy();
//
//    int countAllByCreatedByaAndListingId();
//
//
//
//}
