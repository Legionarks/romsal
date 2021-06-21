package com.github.legionarks.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.github.legionarks.util.Messages;

public class DefaultResource {

    private final Map<String, Object> map;

    @Inject
    Messages messages;

    public DefaultResource() {
        this.map = new HashMap<>();
    }

    public Map<String, Object> getMap() {
        return map;
    }
    
    public Map<String, Object> create() {
        /*map.put("navbar-home", messages.getBundle().getString("home.welcome"));
        map.put("navbar-property", messages.getBundle().getString("home.welcome.phrase.phase.1"));
        map.put("navbar-about", messages.getBundle().getString("home.welcome.phrase.phase.2"));*/
        map.put("navbar-contact", messages.getBundle().getString("home.welcome.phrase.phase.2"));

        return map;
    }

    public Object add(String key, String property) {
        return map.put(key, messages.getBundle().getString(property));
    }
}
