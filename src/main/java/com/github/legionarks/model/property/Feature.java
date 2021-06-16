package com.github.legionarks.model.property;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FEATURE")
public class Feature {

    @Id
    @Column(name = "ID")
    private Short id;

    @Column(name = "TYPE", unique = true)
    private FeatureType type;
}
