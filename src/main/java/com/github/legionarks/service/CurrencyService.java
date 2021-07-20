package com.github.legionarks.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.github.legionarks.dao.CurrencyDao;
import com.github.legionarks.dao.RateDao;
import com.github.legionarks.model.Rate;

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

    public void persist(Rate rate) {
        Boolean found = false;

        if (rateDao.find(new Rate(rate.getOrigin(), rate.getTarget(), null)) == null) {
            rateDao.create(rate);
        } else {
            rateDao.edit(rate);
        }

        for (Rate inverse : rateDao.findAll()) {
            if (rate.getOrigin().getCode().equals(inverse.getTarget().getCode())
                    && rate.getTarget().getCode().equals(inverse.getOrigin().getCode())) {
                inverse.setExchange(BigDecimal.ONE.divide(rate.getExchange(), rate.getExchange().scale() + 1,
                        RoundingMode.HALF_UP));
                rateDao.edit(inverse);
                found = true;
                break;
            }
        }

        if (!found) {
            found = rateDao.create(new Rate(rate.getTarget(), rate.getOrigin(),
                    BigDecimal.ONE.divide(rate.getExchange(), rate.getExchange().scale() + 1, RoundingMode.HALF_UP)));
        }
    }

}
