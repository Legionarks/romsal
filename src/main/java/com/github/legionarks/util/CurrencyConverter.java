package com.github.legionarks.util;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.util.StdConverter;

public class CurrencyConverter extends StdConverter<BigDecimal, String> {

    @Override
    public String convert(BigDecimal value) {
        return "USD$" + value.toString();
    }

}
