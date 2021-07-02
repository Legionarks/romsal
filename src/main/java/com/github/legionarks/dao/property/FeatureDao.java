package com.github.legionarks.dao.property;

import javax.inject.Singleton;

import com.github.legionarks.dao.Datasource;
import com.github.legionarks.model.property.feature.Feature;

@Singleton
public class FeatureDao extends Datasource<Feature> {

    public FeatureDao() {
        super(Feature.class);
    }

    public Feature find(String name) {
        return manager.createQuery("FROM Feature WHERE TYPE = :name", Feature.class).setParameter("name", name)
                .getSingleResult();
    }
}
