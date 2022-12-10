//package com.project.sharenter.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//public class Inquiry extends Auditable<String> {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "first_name")
//    private String Name;
//
//    @Column(name = "email")
//    private String email;
//
//    @Column(name = "phone")
//    private String phone;
//
//    @Column(name = "message")
//    private String message;
//
//    @ManyToOne
//    @JoinColumn(name="listing_id", nullable=false)
//    private Listing listing;
//
//
//}