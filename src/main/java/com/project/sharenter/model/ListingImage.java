//package com.project.sharenter.model;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//
//import javax.persistence.*;
//
//@Data
//@RequiredArgsConstructor
//@Entity
//@Table(name = "listing_image")
//public class ListingImage{
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Lob
//    @Column(name = "image")
//    private byte[] imagem;
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_listing", nullable = false)
//    @JsonBackReference
//    private Listing Listing;
//}
