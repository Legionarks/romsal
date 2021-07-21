package com.github.legionarks.dao;

import javax.inject.Singleton;

import com.github.legionarks.model.currency.Rate;

@Singleton
public class RateDao extends Datasource<Rate> {

    public RateDao() {
        super(Rate.class);
    }
}
