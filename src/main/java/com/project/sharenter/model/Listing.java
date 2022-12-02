package com.project.sharenter.model;


import lombok.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;


@Entity(name = "listings")
@Getter
@Setter
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Listing extends Auditable<String>{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "rent")
    private double rent;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "address")
    //Google Geocoding API results
    private String address;

    private String street;

    private String city;

    private String county;

    private String neighborhood;

    @Column(name = "lat")
    private double lat;

    @Column(name = "lng")
    private double lng;

    //Walkscore API results
    @Column(name = "walkscore")
    private String walkscore;

    @Column(name = "walkscoreDescription")
    private String walkscoreDescription;

    @Column(name = "numHousemates")
    private int numHousemates;

    @Column(nullable=false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean privateBathroom;

    @Column(nullable=false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean petFriendly;

    @Column(nullable=false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean suitableForCouples;

    @Column(nullable=false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean billsIncluded;

    @Column(nullable=false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean parking;

    @Column(nullable=false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean landlordOccupied;

//    @OneToMany(mappedBy = "listing")
//    private List<Viewing> viewings;

    private Boolean available = true;




}
