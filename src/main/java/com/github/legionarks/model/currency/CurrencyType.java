package com.github.legionarks.model.currency;

public enum CurrencyType {
    USD("USD", "United States Dollar"), DOP("DOP", "Dominican Peso");

    private final String code;
    private final String name;

    private CurrencyType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static CurrencyType find(String code) {
        for (CurrencyType type : values()) {
            if (type.getCode().equalsIgnoreCase(code)) {
                return type;
            }
        }

        return null;
    }
}
