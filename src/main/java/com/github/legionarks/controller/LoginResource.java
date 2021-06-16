package com.github.legionarks.controller;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.legionarks.dao.UserDao;
import com.github.legionarks.util.Templates;

import io.quarkus.qute.TemplateInstance;

@Path("login")
public class LoginResource {

    @Inject
    UserDao data;
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance login() {
        return Templates.login();
    }

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed({"ADMIN"})
    public TemplateInstance test() {
        return Templates.login();
    }
}
