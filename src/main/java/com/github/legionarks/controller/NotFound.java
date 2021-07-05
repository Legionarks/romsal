package com.github.legionarks.controller;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.github.legionarks.util.Templates;

@Provider
public class NotFound implements ExceptionMapper<NotFoundException> {

    @Produces(MediaType.TEXT_HTML)
    @Override
    public Response toResponse(NotFoundException exception) {
        return Response.status(Status.NOT_FOUND).entity(Templates.index()).build();
    }
}
