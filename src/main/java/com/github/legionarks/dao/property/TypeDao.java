package com.github.legionarks.dao.property;

import javax.inject.Singleton;

import com.github.legionarks.dao.Datasource;
import com.github.legionarks.model.property.type.Type;

@Singleton
public class TypeDao extends Datasource<Type> {

    public TypeDao() {
        super(Type.class);
    }

    public Type find(String name) {
        return manager.createQuery("FROM Type WHERE TYPE = :name", Type.class).setParameter("name", name).getSingleResult();
    }
}
