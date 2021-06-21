package com.github.legionarks.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.legionarks.util.Messages;
import com.github.legionarks.util.Templates;

import io.quarkus.qute.TemplateInstance;

@Path("")
@Produces(MediaType.TEXT_HTML)
public class HomeResource {

    @GET
    public TemplateInstance main() {
        final DefaultResource resource = new DefaultResource();
        resource.create();
        
        resource.add("welcome", "home.welcome");
        resource.add("welcome-1", "home.welcome.phrase.phase.1");
        resource.add("welcome-2", "home.welcome.phrase.phase.2");

        resource.add("project", "home.project");
        resource.add("project-phrase", "home.project.phrase");

        resource.add("service", "home.service");
        //resource.add("service-1", List.of(messages.getBundle().getString("home.service.title.1"), messages.getBundle().getString("home.service.phrase.1")));
        //resource.add("service-2", List.of(messages.getBundle().getString("home.service.title.2"), messages.getBundle().getString("home.service.phrase.2")));
        //resource.add("service-3", List.of(messages.getBundle().getString("home.service.title.3"), messages.getBundle().getString("home.service.phrase.3")));

        return Templates.index().data("map", resource.getMap());
    }
}
