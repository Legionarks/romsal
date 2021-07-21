package com.github.legionarks.dao;

import javax.inject.Singleton;

import com.github.legionarks.model.currency.Currency;
import com.github.legionarks.model.currency.CurrencyType;

@Singleton
public class CurrencyDao extends Datasource<Currency> {

    public CurrencyDao() {
        super(Currency.class);
    }

    public Currency find(CurrencyType type) {
        return manager.createQuery("FROM Currency WHERE type = :type", clazz).setParameter("type", type)
                .getSingleResult();
    }
}
