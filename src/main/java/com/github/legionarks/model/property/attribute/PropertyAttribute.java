package com.github.legionarks.model.property.attribute;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.github.legionarks.model.property.Property;

@Entity
@Table(name = "PROPERTY_ATTRIBUTE")
public class PropertyAttribute implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "PROPERTY_ID")
    private Property property;

    @Id
    @OneToOne
    @JoinColumn(name = "ATTRIBUTE_ID")
    private Attribute attribute;

    @Column(name = "AMOUNT")
    private Short amount;

    public PropertyAttribute() {
    }

    public PropertyAttribute(Attribute attribute, Short amount) {
        this.attribute = attribute;
        this.amount = amount;
    }

}
