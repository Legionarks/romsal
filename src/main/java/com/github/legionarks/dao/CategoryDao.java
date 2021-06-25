package com.github.legionarks.dao;

import javax.inject.Singleton;

import com.github.legionarks.model.property.Category;

@Singleton
public class CategoryDao extends Datasource<Category> {

    public CategoryDao() {
        super(Category.class);
    }

    public Category find(String name) {
        return manager.createQuery("FROM Category WHERE TYPE = ?1", Category.class).setParameter(1, name)
                .getSingleResult();
    }
}