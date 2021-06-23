package com.github.legionarks.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {

    private String language;
    private String country;
    private Locale locale;
    private ResourceBundle bundle;

    public Messages() {
        this.language = "es";
        this.country = "do";
        this.locale = new Locale(language, country);
        this.bundle = ResourceBundle.getBundle("locales/messages", locale);
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

}
