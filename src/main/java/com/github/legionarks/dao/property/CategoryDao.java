package com.github.legionarks.dao.property;

import javax.inject.Singleton;

import com.github.legionarks.dao.Datasource;
import com.github.legionarks.model.property.category.Category;

@Singleton
public class CategoryDao extends Datasource<Category> {

    public CategoryDao() {
        super(Category.class);
    }

    public Category find(String name) {
        return manager.createQuery("FROM Category WHERE TYPE = :name", Category.class).setParameter("name", name)
                .getSingleResult();
    }
}
