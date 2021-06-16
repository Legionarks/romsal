package com.github.legionarks.model.property;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LOCATION")
public class Location {

    @Id
    @Column(name = "PROPERTY_ID")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "PROPERTY_ID")
    private Property property;

    @Column(name = "LATITUDE")
    private Float latitude;

    @Column(name = "LONGITUDE")
    private Float longitude;

}
