package com.github.legionarks.controller;

import java.util.HashMap;
import java.util.Map;

import com.github.legionarks.util.Messages;

class Transcript {

    private final Map<String, Object> map;

    private final Messages messages;

    public Transcript() {
        this.map = new HashMap<>();
        this.messages = new Messages();
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public Transcript defaults() {
        map.put("navbar-home", messages.getBundle().getString("navbar.home"));
        map.put("navbar-property", messages.getBundle().getString("navbar.property"));
        map.put("navbar-about", messages.getBundle().getString("navbar.about"));
        map.put("navbar-contact", messages.getBundle().getString("navbar.contact"));

        return this;
    }

    public Object put(String key, Object value) {
        return map.put(key, value instanceof String ? messages.getBundle().getString((String) value) : value);
    }

    public String get(String property) {
        return messages.getBundle().getString(property);
    }
}
