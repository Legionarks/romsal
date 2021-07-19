package com.github.legionarks.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.github.legionarks.dao.CurrencyDao;
import com.github.legionarks.dao.RateDao;

@ApplicationScoped
public class CurrencyService {

    @Inject
    CurrencyDao currencyDao;

    @Inject
    RateDao rateDao;

    public CurrencyDao getCurrencyDao() {
        return currencyDao;
    }

    public void setCurrencyDao(CurrencyDao currencyDao) {
        this.currencyDao = currencyDao;
    }

    public RateDao getRateDao() {
        return rateDao;
    }

    public void setRateDao(RateDao rateDao) {
        this.rateDao = rateDao;
    }

}
