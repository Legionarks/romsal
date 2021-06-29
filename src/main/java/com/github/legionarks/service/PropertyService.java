package com.github.legionarks.service;

import java.math.BigDecimal;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.github.legionarks.dao.property.CategoryDao;
import com.github.legionarks.dao.property.PropertyDao;
import com.github.legionarks.dao.property.TypeDao;
import com.github.legionarks.model.property.Property;
import com.github.legionarks.model.property.category.Category;
import com.github.legionarks.model.property.category.CategoryType;
import com.github.legionarks.model.property.type.PropertyType;
import com.github.legionarks.model.property.type.Type;

@ApplicationScoped
public class PropertyService {

    @Inject
    PropertyDao data;

    @Inject
    CategoryDao categoryData;

    @Inject
    TypeDao typeDao;

    public void categories() {
        Category category;

        for (CategoryType type : CategoryType.values()) {
            category = new Category();
            category.setType(type);
            categoryData.create(category);
        }
    }

    public void types() {
        Type type;

        for (PropertyType xtype : PropertyType.values()) {
            type = new Type();
            type.setType(xtype);
            typeDao.create(type);
        }
    }

    public void add(String name, String description, BigDecimal price, CategoryType category, Boolean outstanding) {
        Property property = new Property();

        property.setName(name);
        property.setDescription(description);
        property.setPrice(price);
        property.setCategory(categoryData.find(category.name()));
        property.setOutstanding(outstanding);

        data.create(property);
    }

    public PropertyDao getData() {
        return data;
    }

    public void setData(PropertyDao data) {
        this.data = data;
    }

    public CategoryDao getCategoryData() {
        return categoryData;
    }

    public void setCategoryData(CategoryDao categoryData) {
        this.categoryData = categoryData;
    }
}
