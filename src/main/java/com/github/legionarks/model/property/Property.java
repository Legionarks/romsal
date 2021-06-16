package com.github.legionarks.model.property;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "PROPERTY")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private BigDecimal price;

    @OneToMany(mappedBy = "property")
    private Set<PropertyAttribute> attributes;

    @ManyToMany
    @Column(name = "FEATURES")
    private Set<Feature> features;

    @OneToMany(mappedBy = "property")
    private Set<Media> medias;

    @Column(name = "ADDRESS")
    private String address;

    @Lob
    @Column(name = "SUMMARY")
    private String summary;

    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "LOCATION")
    private Location location;

    @Column(name = "ADD_DATE")
    private Date addDate;

    @Column(name = "EDIT_DATE")
    private Date editDate;

}
