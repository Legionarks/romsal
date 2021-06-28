package com.github.legionarks.service;

import java.math.BigDecimal;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.github.legionarks.dao.CategoryDao;
import com.github.legionarks.dao.PropertyDao;
import com.github.legionarks.model.property.Category;
import com.github.legionarks.model.property.CategoryType;
import com.github.legionarks.model.property.Property;

@ApplicationScoped
public class PropertyService {

    @Inject
    PropertyDao data;

    @Inject
    CategoryDao categoryData;

    public void categories() {
        Category category;

        category = new Category();
        category.setType(CategoryType.SELL);
        categoryData.create(category);

        category = new Category();
        category.setType(CategoryType.RENT);
        categoryData.create(category);

        System.out.println("\n\n\n\n" + categoryData.find(CategoryType.RENT.name()));
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
