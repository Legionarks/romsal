package com.github.legionarks.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.github.legionarks.dao.property.AttributeDao;
import com.github.legionarks.dao.property.CategoryDao;
import com.github.legionarks.dao.property.FeatureDao;
import com.github.legionarks.dao.property.PropertyDao;
import com.github.legionarks.dao.property.TypeDao;
import com.github.legionarks.model.property.attribute.Attribute;
import com.github.legionarks.model.property.attribute.AttributeType;
import com.github.legionarks.model.property.category.Category;
import com.github.legionarks.model.property.category.CategoryType;
import com.github.legionarks.model.property.type.PropertyType;
import com.github.legionarks.model.property.type.Type;

@ApplicationScoped
public class PropertyService {

    @Inject
    PropertyDao propertyDao;

    @Inject
    CategoryDao categoryDao;

    @Inject
    TypeDao typeDao;

    @Inject
    AttributeDao attributeDao;

    @Inject
    FeatureDao featureDao;

    public PropertyDao getPropertyDao() {
        return propertyDao;
    }

    public void setPropertyDao(PropertyDao propertyDao) {
        this.propertyDao = propertyDao;
    }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public TypeDao getTypeDao() {
        return typeDao;
    }

    public void setTypeDao(TypeDao typeDao) {
        this.typeDao = typeDao;
    }

    public AttributeDao getAttributeDao() {
        return attributeDao;
    }

    public void setAttributeDao(AttributeDao attributeDao) {
        this.attributeDao = attributeDao;
    }

    public FeatureDao getFeatureDao() {
        return featureDao;
    }

    public void setFeatureDao(FeatureDao featureDao) {
        this.featureDao = featureDao;
    }

    public void categories() {
        Category category;

        for (CategoryType type : CategoryType.values()) {
            category = new Category();
            category.setType(type);
            categoryDao.create(category);
        }
    }

    public void attributes() {
        Attribute attribute;

        for (AttributeType type : AttributeType.values()) {
            attribute = new Attribute();
            attribute.setType(type);
            attributeDao.create(attribute);
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
}
