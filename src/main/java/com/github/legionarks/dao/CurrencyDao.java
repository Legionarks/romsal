package com.github.legionarks.dao;

import javax.inject.Singleton;

import com.github.legionarks.model.Currency;

@Singleton
public class CurrencyDao extends Datasource<Currency> {

    public CurrencyDao() {
        super(Currency.class);
    }
}
