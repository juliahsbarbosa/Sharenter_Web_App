//package com.project.sharenter.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.*;
//import lombok.experimental.SuperBuilder;
//
//import javax.persistence.DiscriminatorValue;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.OneToMany;
//import java.util.List;
//
//@Entity
//@DiscriminatorValue(value = "2")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString(exclude = {"listings"}, callSuper = true)
//@EqualsAndHashCode(callSuper = true)
//@SuperBuilder
//public class Sharer extends User{
//    private String name;
//
//    private String creci;
//
//    @JsonIgnore
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sharer")
//    private List<Listing> listings;
//}
