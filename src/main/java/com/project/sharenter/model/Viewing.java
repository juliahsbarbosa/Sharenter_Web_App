//package com.project.sharenter.model;
//
//import com.sun.istack.NotNull;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import javax.persistence.*;
//import java.util.Date;
//
//public class Viewing {
//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
//    private Long id;
//    @ManyToOne(cascade=CascadeType.ALL)
//    private Listing listing;
//    @ManyToOne(cascade= CascadeType.ALL)
//    private Renter renter;
//
//}
