package com.github.legionarks.controller.common;

import com.github.legionarks.controller.Transcript;

public final class CommonTranscript extends Transcript {

    @Override
    public Transcript defaults() {
        map.put("navbar-home", messages.getBundle().getString("navbar.home"));
        map.put("navbar-property", messages.getBundle().getString("navbar.property"));
        map.put("navbar-about", messages.getBundle().getString("navbar.about"));
        map.put("navbar-contact", messages.getBundle().getString("navbar.contact"));

        return this;
    }

}
