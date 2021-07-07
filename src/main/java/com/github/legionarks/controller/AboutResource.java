package com.github.legionarks.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.legionarks.util.Templates;

import io.quarkus.qute.TemplateInstance;

@Path("about")
@Produces(MediaType.TEXT_HTML)
public class AboutResource {

    @GET
    public TemplateInstance main() {
        final Transcript transcript = new Transcript().defaults();

        transcript.getMap().put("page", "about");
        transcript.put("title", "about.title");
        transcript.put("phrase", "about.phrase");
        transcript.put("mission-title", "about.mission.title");
        transcript.put("mission", "about.mission");
        transcript.put("vision-title", "about.vision.title");
        transcript.put("vision", "about.vision");
        transcript.put("values-title", "about.values.title");
        transcript.put("values", "about.values");

        return Templates.about().data("map", transcript.getMap());
    }
}
