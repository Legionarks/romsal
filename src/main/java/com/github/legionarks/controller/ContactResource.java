package com.github.legionarks.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.legionarks.util.Templates;

import io.quarkus.qute.TemplateInstance;

@Path("contact")
@Produces(MediaType.TEXT_HTML)
public class ContactResource {

    @GET
    public TemplateInstance main() {
        final Transcript transcript = new Transcript().defaults();

        transcript.put("title", "contact.title");
        transcript.put("phrase", "contact.phrase");
        transcript.put("form-name", "contact.form.name");
        transcript.put("form-telephone", "contact.form.telephone");
        transcript.put("form-email", "contact.form.email");
        transcript.put("form-message", "contact.form.message");
        transcript.put("form-send", "contact.form.send");
        transcript.put("title-info", "contact.title.info");

        return Templates.contact().data("map", transcript.getMap());
    }
}
