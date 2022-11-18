//package com.project.sharenter.model;
//
//import com.sun.istack.NotNull;
//import lombok.*;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotEmpty;
//import java.awt.*;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class Listing implements Serializable {
//    public static final long serialVersionUID = 1L;
//
//    @Id
//    @Column(name = "id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_anuncio")
//    @SequenceGenerator(name = "seq_anuncio", sequenceName = "anuncio_id_seq", schema = "public", allocationSize = 1)
//    @EqualsAndHashCode.Include
//    private Integer id;
//
//    @Column(name = "title", nullable = false)
//    @NotNull
//    private String title;
//
//    @Column(name = "descricao", nullable = false)
//    @NotNull
//    private String description;
//
//    @Column(name = "price", nullable = false)
//    @NotNull
//    private Double price;
//
//    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.DETACH}, fetch = FetchType.EAGER)
//    @JoinColumn(name = "id_sharer", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_listing_sharer"))
//    private Sharer sharer;
//
//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_endereco", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_anuncio_endereco"))
//    private Address address;
//
//    @Column(name = "tipo_imovel", nullable = false)
//    @NotNull
//    private String roomType;
//
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "anuncio")
//    private List<Pics> listingPics;
//
//    @Embedded
//    private Address address;
//
//    @Embedded
//    private Geolocation geoLocation;
//
//    @NotNull @NotEmpty
//    private int housemates;
//
//    @NotNull @NotEmpty
//    private String bathroomType;
//
//    @NotNull @NotEmpty
//    private boolean petFriendly;
//
//}
