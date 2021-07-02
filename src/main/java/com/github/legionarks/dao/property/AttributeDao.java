package com.github.legionarks.dao.property;

import javax.inject.Singleton;

import com.github.legionarks.dao.Datasource;
import com.github.legionarks.model.property.attribute.Attribute;

@Singleton
public class AttributeDao extends Datasource<Attribute> {

    public AttributeDao() {
        super(Attribute.class);
    }

    public Attribute find(String name) {
        return manager.createQuery("FROM Attribute WHERE TYPE = :name", Attribute.class).setParameter("name", name).getSingleResult();
    }
}
