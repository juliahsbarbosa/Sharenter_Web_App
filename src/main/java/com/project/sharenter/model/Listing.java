//package com.project.sharenter.model;
//
//import com.sun.istack.NotNull;
//import lombok.*;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotEmpty;
//import java.awt.*;
//import java.io.Serializable;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//
//public class Listing implements Serializable {
//    public static final long serialVersionUID = 1L;
//
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_listing")
//    @SequenceGenerator(name = "seq_listing", sequenceName = "listing_id_seq", schema = "public", allocationSize = 1)
//    private Integer id;
//
//    @Column(name = "title")
//    @NotNull
//    private String title;
//
//    @Column(name = "descricao")
//    @NotNull
//    private String description;
//
//    @Column(name = "rent")
//    @NotNull
//    private Double rent;
//
//    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.DETACH}, fetch = FetchType.EAGER)
//    @JoinColumn(name = "id_sharer", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_listing_sharer"))
//    private Sharer sharer;
//
//    @Column(name = "room_Type")
//    @NotBlank
//    private RoomType roomType;
//
////    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "anuncio")
//////    private List<Pics> listingPics;
//
//    @Embedded
//    private Address address;
//
//    @Embedded
//    private Geocoding geocoding;
//
//    private boolean geoProcessed = false;
//
//    @NotBlank
//    private int housemates;
//
//    @NotBlank
//    private boolean privateBathroom;
//
//    @NotBlank
//    private boolean petFriendly;
//
//    @NotBlank
//    private boolean suitableForCouples;
//
//    @NotBlank
//    private boolean billsIncluded;
//
//    @NotBlank
//    private boolean parking;
//
//    @NotBlank
//    private boolean landlordOccupied;
//
//    @DateTimeFormat
//    private String availability;
//
//
//    public Listing(final String title, final Double rent, final Address address) {
//        this.title = title;
//        this.rent = rent;
//        this.address = address;
//    }
//}
