package com.github.legionarks.controller.admin;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.legionarks.dao.UserDao;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

@Path("admin/login")
public class LoginResource {

    @Inject
    UserDao data;

    @Location("admin/login.html")
    Template login;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance login() {
        return login.instance();
    }

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed({ "ADMIN" })
    public TemplateInstance test() {
        return login.instance();
    }
}
