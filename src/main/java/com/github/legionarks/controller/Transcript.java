package com.github.legionarks.controller;

import java.util.HashMap;
import java.util.Map;

import com.github.legionarks.util.Messages;

public abstract class Transcript {

    protected final Map<String, Object> map;
    protected final Messages messages;

    public Transcript() {
        this.map = new HashMap<>();
        this.messages = new Messages();
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public Messages getMessages() {
        return messages;
    }

    public Object put(String key, Object value) {
        return map.put(key, value instanceof String ? messages.getBundle().getString((String) value) : value);
    }

    public String get(String property) {
        return messages.getBundle().getString(property);
    }

    public abstract Transcript defaults();
}
