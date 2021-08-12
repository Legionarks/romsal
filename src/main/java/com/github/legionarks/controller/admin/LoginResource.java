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

@Path("login")
public class LoginResource {

    @Inject
    UserDao data;

    @Location("common/index.html")
    Template index;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance login() {
        return index.instance();
    }

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed({ "ADMIN" })
    public TemplateInstance test() {
        return index.instance();
    }
}
