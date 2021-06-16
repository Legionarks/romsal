package com.github.legionarks.model.property;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PROPERTY_ATTRIBUTE")
public class PropertyAttribute implements Serializable {

    @Id
    @OneToOne
    private Attribute attribute;

    @ManyToOne
    @JoinColumn(name = "PROPERTY_ID", nullable = false)
    private Property property;

    private Short amount;

}
