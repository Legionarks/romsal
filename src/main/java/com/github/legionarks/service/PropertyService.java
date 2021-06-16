package com.github.legionarks.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.github.legionarks.dao.PropertyDao;
import com.github.legionarks.model.property.Property;

@ApplicationScoped
public class PropertyService {
    
    @Inject
    PropertyDao data;

    public void add() {
        Property property = new Property();

        data.create(property);
    }
}
