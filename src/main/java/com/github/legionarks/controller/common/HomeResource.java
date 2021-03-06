package com.github.legionarks.controller.common;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.legionarks.controller.Transcript;
import com.github.legionarks.service.PropertyService;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

@Path("")
public class HomeResource {

    @Inject
    PropertyService propertyService;

    @Location("common/index.html")
    Template index;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance main() {
        final Transcript transcript = new CommonTranscript().defaults();

        transcript.getMap().put("page", "home");
        
        transcript.put("welcome", "home.welcome");
        transcript.put("welcome-1", "home.welcome.phrase.phase.1");
        transcript.put("welcome-2", "home.welcome.phrase.phase.2");

        transcript.put("project", "home.project");
        transcript.put("project-phrase", "home.project.phrase");

        transcript.put("service", "home.service");
        transcript.put("service-1", List.of(transcript.get("home.service.title.1"), transcript.get("home.service.phrase.1")));
        transcript.put("service-2", List.of(transcript.get("home.service.title.2"), transcript.get("home.service.phrase.2")));
        transcript.put("service-3", List.of(transcript.get("home.service.title.3"), transcript.get("home.service.phrase.3")));

        transcript.put("properties", propertyService.getPropertyDao().findAll());

        return index.data("map", transcript.getMap());
    }
}
