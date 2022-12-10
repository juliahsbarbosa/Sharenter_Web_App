package com.project.sharenter.model;


import lombok.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "listings")
@Getter
@Setter
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Listing extends Auditable<String>{
    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    @Column(name = "rent")
    private BigDecimal rent;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "numHousemates")
    private int numHousemates;

    @Column(nullable=false, columnDefinition = "boolean default false")
    private boolean privateBathroom;

    @Column(nullable=false, columnDefinition = "boolean default false")
    private boolean petFriendly;

    @Column(nullable=false, columnDefinition = "boolean default false")
    private boolean suitableForCouples;

    @Column(nullable=false, columnDefinition = "boolean default false")
    private boolean billsIncluded;

    @Column(nullable=false, columnDefinition = "boolean default false")
    private boolean parking;

    @Column(nullable=false, columnDefinition = "boolean default false")
    private boolean landlordOccupied;

    private Boolean available = true;

    //Google Geocoding API results
    @Column(name = "address")
    private String address;

    @Column(name = "lat")
    private double lat;

    @Column(name = "lng")
    private double lng;

    //Walkscore API results
    @Column(name = "walkscore")
    private String walkscore;

    @Column(name = "walkscoreDescription")
    private String walkscoreDescription;

    @OneToMany(mappedBy = "listing" , fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Inquiry> inquiryList = new ArrayList<>();

}
