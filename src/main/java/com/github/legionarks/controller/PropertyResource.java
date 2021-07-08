package com.github.legionarks.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.github.legionarks.model.property.Property;
import com.github.legionarks.service.PropertyService;
import com.github.legionarks.util.Templates;

import io.quarkus.qute.TemplateInstance;

@Path("property")
public class PropertyResource {

    @Inject
    PropertyService service;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance main() {
        final Transcript transcript = new Transcript().defaults();

        transcript.put("title", "property.search.title");
        transcript.put("phrase", "property.search.phrase");
        transcript.put("category-all", "property.search.form.category.all");
        transcript.put("category-sell", "property.search.form.category.sell");
        transcript.put("category-rent", "property.search.form.category.rent");
        transcript.put("form-name", "property.search.form.name");
        transcript.put("form-type", "property.search.form.type");
        transcript.put("form-currency", "property.search.form.currency");
        transcript.put("form-search", "property.search");

        return Templates.properties().data("map", transcript.getMap());
    }

    @GET
    @Path("info")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance info(@QueryParam("id") Long id) {
        final Transcript transcript = new Transcript();
        Property property = service.getPropertyDao().find(id);

        transcript.defaults();

        transcript.put("category-all", "property.search.form.category.all");
        transcript.put("category-sell", "property.search.form.category.sell");
        transcript.put("category-rent", "property.search.form.category.rent");
        transcript.put("form-name", "property.search.form.name");
        transcript.put("form-type", "property.search.form.type");
        transcript.put("form-currency", "property.search.form.currency");

        transcript.put("property", property);

        return Templates.info().data("map", transcript.getMap());
    }

    @GET
    @Path("search")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance search(@QueryParam("page") Short page, @QueryParam("project") String project, @QueryParam("type") String type,
            @QueryParam("bath") String bath, @QueryParam("room") String room, @QueryParam("category") String category) {
        final Transcript transcript = new Transcript();

        System.out.println("INI");
        System.out.println(page);
        System.out.println(project);
        System.out.println(type);
        System.out.println(bath);
        System.out.println(room);
        System.out.println(category);

        //System.out.println(service.find(page, project, type, bath, room, category));
        System.out.println("FIN");

        transcript.defaults();

        transcript.put("orders", "property.search.order.newer");
        transcript.put("filter", "property.search.filter");
        transcript.put("form-name", "property.search.form.name");
        transcript.put("form-type", "property.search.form.type");

        transcript.put("form-bath", "property.search.form.bath");
        transcript.put("form-room", "property.search.form.room");
        transcript.put("form-category", "property.search.form.category");
        transcript.put("form-currency", "property.search.form.currency");
        transcript.put("form-search", "property.search");

        return Templates.search().data("map", transcript.getMap());
    }

    private enum Category {
        ALL("property.search.form.category.all"), ANY("property.search.form.category.any"),
        SELL("property.search.form.category.sell"), RENT("property.search.form.category.rent");

        private final String property;

        private Category(String property) {
            this.property = property;
        }

        public String getProperty() {
            return property;
        }
    }

    private enum Order {
        NEWER("property.search.order.newer"), OLDER("property.search.order.older"),
        PRICE_LOW("property.search.order.price.low"), PRICE_HIGH("property.search.order.price.high"),
        OUTSTANDING("property.search.order.outstanding");

        private final String property;

        private Order(String property) {
            this.property = property;
        }

        public String getProperty() {
            return property;
        }
    }
}
