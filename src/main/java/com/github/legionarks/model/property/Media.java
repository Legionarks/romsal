package com.github.legionarks.model.property;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MEDIA")
public class Media implements Serializable {

    @Id
    @Column(name = "ID")
    private Long id;

    @Id
    @ManyToOne
    @JoinColumn(name = "PROPERTY_ID")
    private Property property;

    @Column(name = "NAME")
    private String name;
}
