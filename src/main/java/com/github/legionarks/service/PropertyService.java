package com.github.legionarks.service;

import java.math.BigDecimal;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.github.legionarks.dao.PropertyDao;
import com.github.legionarks.model.property.Category;
import com.github.legionarks.model.property.CategoryType;
import com.github.legionarks.model.property.Property;

@ApplicationScoped
public class PropertyService {
    
    @Inject
    PropertyDao data;

    public void add(String name, String description, BigDecimal price, CategoryType category, Boolean outstanding) {
        Property property = new Property();

        property.setName(name);
        property.setDescription(description);
        property.setPrice(price);
        property.setOutstanding(outstanding);

        data.create(property);
    }
}
