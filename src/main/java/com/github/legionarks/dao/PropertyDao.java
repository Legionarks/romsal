package com.github.legionarks.dao;

import javax.inject.Singleton;

import com.github.legionarks.model.property.Property;

@Singleton
public class PropertyDao extends Datasource<Property> {

    public PropertyDao() {
        super(Property.class);
    }
}
