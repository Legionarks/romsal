package com.github.legionarks.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.legionarks.util.Templates;

import io.quarkus.qute.TemplateInstance;

@Path("hello-resteasy")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public TemplateInstance hello() {
        return Templates.login();
    }
}
