package com.project.sharenter.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.awt.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Listing extends Auditable<String>{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_listing")
    @SequenceGenerator(name = "seq_listing", sequenceName = "listing_id_seq", schema = "public", allocationSize = 1)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "rent")
    private Double rent;

    @Column(name = "room_Type")
    private RoomType roomType;
    @Embedded
    private Address address;

    @Embedded
    private Geocoding geocoding;

    private Boolean geoProcessed = false;

    private int housemates;

    private Boolean privateBathroom;

    private Boolean petFriendly;

    private Boolean suitableForCouples;

    private Boolean billsIncluded;

    private Boolean parking;

    private Boolean landlordOccupied;

    @DateTimeFormat
    private String availability;

    public Listing(final String title, final Double rent, final Address address) {
        this.title = title;
        this.rent = rent;
        this.address = address;
    }
}
