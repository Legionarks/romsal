package com.github.legionarks.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.github.legionarks.model.Location;
import com.github.legionarks.model.property.Property;
import com.github.legionarks.service.ContactService;
import com.github.legionarks.service.PropertyService;

@Path("api")
public class ApiResource {

    @Inject
    PropertyService propertyService;

    @Inject
    ContactService contactService;

    @GET
    @Path("properties")
    public List<Property> properties() {
        return propertyService.getPropertyDao().findAll();
    }

    @GET
    @Path("search")
    public List<Property> search() {
        return propertyService.getPropertyDao().find(null, null, null, null, null, null, null, null);
    }

    @GET
    @Path("locations")
    public List<Location> locations() {
        return contactService.getData().locations();
    }

    @GET
    @Path("property/location")
    public Location location(@QueryParam("id") Long id) {
        return propertyService.getPropertyDao().find(id).getLocation();
    }
}
