package com.github.legionarks.controller.common;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import com.github.legionarks.controller.Transcript;

@Path("about")
@Produces(MediaType.TEXT_HTML)
public class AboutResource {

    @Location("common/about.html")
    Template about;

    @GET
    public TemplateInstance main() {
        final Transcript transcript = new CommonTranscript().defaults();

        transcript.getMap().put("page", "about");
        transcript.defaults();

        transcript.put("title", "about.title");
        transcript.put("phrase", "about.phrase");
        transcript.put("mission-title", "about.mission.title");
        transcript.put("mission", "about.mission");
        transcript.put("vision-title", "about.vision.title");
        transcript.put("vision", "about.vision");
        transcript.put("values-title", "about.values.title");
        transcript.put("values", "about.values");

        return about.data("map", transcript.getMap());
    }
}
