package com.github.legionarks.model.property;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY")
public class Category {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private CategoryType name;

}
