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

@Path("contact")
@Produces(MediaType.TEXT_HTML)
public class ContactResource {
    
    @Inject
    Messages messages;

    @GET
    public TemplateInstance main() {
        Map<String, String> map = new HashMap<>();
        
        map.put("title", messages.getBundle().getString("contact.title"));
        map.put("phrase", messages.getBundle().getString("contact.phrase"));
        map.put("form-name", messages.getBundle().getString("contact.form.name"));
        map.put("form-telephone", messages.getBundle().getString("contact.form.telephone"));
        map.put("form-email", messages.getBundle().getString("contact.form.email"));
        map.put("form-message", messages.getBundle().getString("contact.form.message"));
        map.put("form-send", messages.getBundle().getString("contact.form.send"));
        map.put("title-info", messages.getBundle().getString("contact.title.info"));

        return Templates.contact().data("map", map);
    }
}
