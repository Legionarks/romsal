package com.github.legionarks.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.legionarks.util.Messages;
import com.github.legionarks.util.Templates;

import io.quarkus.qute.TemplateInstance;

@Path("hello-resteasy")
@Produces(MediaType.TEXT_HTML)
public class GreetingResource {

    @Inject
    Messages messages;

    @GET
    public TemplateInstance hello() {
        Map<String, String> map = new HashMap<>();
        
        map.put("title", messages.getBundle().getString("home.title"));
        map.put("phase-1", messages.getBundle().getString("home.phrase.phase-1"));
        map.put("phase-2", messages.getBundle().getString("home.phrase.phase-2"));
        
        return Templates.index().data("map", map);
    }
}
