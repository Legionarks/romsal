package com.github.legionarks.controller.common;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;

@Provider
public class NotFound implements ExceptionMapper<NotFoundException> {

    @Location("common/error.html")
    Template error;

    @Produces(MediaType.TEXT_HTML)
    @Override
    public Response toResponse(NotFoundException exception) {
        return Response.status(Status.NOT_FOUND).entity(error.instance()).build();
    }
}
