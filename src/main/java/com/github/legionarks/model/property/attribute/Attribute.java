package com.github.legionarks.model.property.attribute;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ATTRIBUTE")
public class Attribute {

    @Id
    @Column(name = "ID")
    private Short id;

    @Column(name = "TYPE", unique = true)
    private AttributeType type;

}