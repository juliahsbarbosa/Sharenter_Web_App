package com.project.sharenter.model;


import lombok.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Entity
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

    @Column(name = "room_Type")
    private RoomType roomType;

    private String image;

    private String address;

    private double lat;

    private double lng;

    private String placeId;

    private String walkscore;

    private String description;

    private int housemates;

    @Column(nullable=false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean privateBathroom;

    @Column(nullable=false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean petFriendly = true;

    @Column(nullable=false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean suitableForCouples;

    @Column(nullable=false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean billsIncluded;

    @Column(nullable=false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean parking;

    @Column(nullable=false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean landlordOccupied;

}
