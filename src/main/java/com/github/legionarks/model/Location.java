package com.github.legionarks.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOCATION")
public class Location {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "LATITUDE")
    private Float latitude;

    @Column(name = "LONGITUDE")
    private Float longitude;

    public void set(Float latitude, Float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
