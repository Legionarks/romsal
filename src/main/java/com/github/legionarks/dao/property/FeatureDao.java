package com.github.legionarks.dao.property;

import javax.inject.Singleton;

import com.github.legionarks.dao.Datasource;
import com.github.legionarks.model.property.Feature;

@Singleton
public class FeatureDao extends Datasource<Feature> {

    public FeatureDao() {
        super(Feature.class);
    }
}
