package com.github.legionarks.controller;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.legionarks.service.ContactService;
import com.github.legionarks.util.Templates;

import org.eclipse.microprofile.config.ConfigProvider;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.qute.TemplateInstance;

@Path("contact")
public class ContactResource {

    @Inject
    ContactService service;

    @Inject
    Mailer mailer;

    @GET
    @Produces(MediaType.TEXT_HTML)
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
        
        transcript.put("contacts", service.getData().findAll());

        return Templates.contact().data("map", transcript.getMap());
    }

    @POST
    public void form(@FormParam("name") String name, @FormParam("telephone") String telephone,
            @FormParam("email") String email, @FormParam("message") String message) {
        String from = ConfigProvider.getConfig().getValue("quarkus.mailer.from", String.class);

        mailer.send(Mail.withText(from, "Nuevo mensaje", message + '\n' + name + '\n' + telephone));
    }
}
