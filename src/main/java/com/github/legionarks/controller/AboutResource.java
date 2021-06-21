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

@Path("about")
@Produces(MediaType.TEXT_HTML)
public class AboutResource {

    @Inject
    Messages messages;

    @GET
    public TemplateInstance main() {
        Map<String, String> map = new HashMap<>();
        
        map.put("title", messages.getBundle().getString("about.title"));
        map.put("phrase", messages.getBundle().getString("about.phrase"));
        map.put("mission-title", messages.getBundle().getString("about.mission.title"));
        map.put("mission", messages.getBundle().getString("about.mission"));
        map.put("vision-title", messages.getBundle().getString("about.vision.title"));
        map.put("vision", messages.getBundle().getString("about.vision"));
        map.put("values-title", messages.getBundle().getString("about.values.title"));
        map.put("values", messages.getBundle().getString("about.values"));
        
        return Templates.about().data("map", map);
    }
}
