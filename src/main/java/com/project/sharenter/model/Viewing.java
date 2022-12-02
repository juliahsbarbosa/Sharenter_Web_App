//package com.project.sharenter.model;
//
//import lombok.*;
//import org.hibernate.annotations.ColumnDefault;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@Entity
//@EntityListeners(AuditingEntityListener.class)
//public class Viewing extends Auditable<String>{
//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private Long id;
//    @ManyToOne
//    @JoinColumn(name = "listing_id")
//    private Listing listing;
//
//    @Column
//    private LocalDateTime date;
//
//    @Column(columnDefinition = "1")
//    private boolean available;
//}
