//package com.project.sharenter.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.*;
//import lombok.experimental.SuperBuilder;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@DiscriminatorValue(value = "2")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//public class Sharer extends User{
//    @Id
//    private String email;
//
//    private String name;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sharer")
//    private List<Listing> listings;
//
////    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sharer")
////    private List<Viewing> viewings;
//
//
//}
