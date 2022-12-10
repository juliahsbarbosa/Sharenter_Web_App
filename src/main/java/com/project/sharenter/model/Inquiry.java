/*
 * This entity was going to be used as Inquiry contact form, but I could not implement the
 * @OnetoMany, @ManyToOne  relationship. I always got the following error:
 * Caused by: java.sql.SQLIntegrityConstraintViolationException: Column 'listing_id' cannot be null
 * I've tried different methods to set the listing_id, but none of them worked.
 */

package com.project.sharenter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Inquiry extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String Name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "message")
    private String inquiry;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="listing_id", referencedColumnName = "id", nullable = false)
    private Listing listing;

}
