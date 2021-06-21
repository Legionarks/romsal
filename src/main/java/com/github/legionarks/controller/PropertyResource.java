package com.github.legionarks.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.github.legionarks.util.Messages;
import com.github.legionarks.util.Templates;

import io.quarkus.qute.TemplateInstance;

@Path("property")
@Produces(MediaType.TEXT_HTML)
public class PropertyResource {

    @Inject
    Messages messages;

    @GET
    public TemplateInstance main() {
        Map<String, String> map = new HashMap<>();

        map.put("title", messages.getBundle().getString("property.search.title"));
        map.put("phrase", messages.getBundle().getString("property.search.phrase"));
        map.put("category-all", messages.getBundle().getString("property.search.form.category.all"));
        map.put("category-sell", messages.getBundle().getString("property.search.form.category.sell"));
        map.put("category-rent", messages.getBundle().getString("property.search.form.category.rent"));
        map.put("form-name", messages.getBundle().getString("property.search.form.name"));
        map.put("form-type", messages.getBundle().getString("property.search.form.type"));
        map.put("form-currency", messages.getBundle().getString("property.search.form.currency"));
        map.put("form-search", messages.getBundle().getString("property.search"));

        return Templates.property().data("map", map);
    }

    @GET
    @Path("info")
    public TemplateInstance info(@QueryParam("id") Integer id) {
        Map<String, String> map = new HashMap<>();

        map.put("category-all", messages.getBundle().getString("property.search.form.category.all"));
        map.put("category-sell", messages.getBundle().getString("property.search.form.category.sell"));
        map.put("category-rent", messages.getBundle().getString("property.search.form.category.rent"));
        map.put("form-name", messages.getBundle().getString("property.search.form.name"));
        map.put("form-type", messages.getBundle().getString("property.search.form.type"));
        map.put("form-currency", messages.getBundle().getString("property.search.form.currency"));

        return Templates.info().data("map", map);
    }

    @GET
    @Path("search")
    public TemplateInstance search() {
        Map<String, String> map = new HashMap<>();

        map.put("orders", messages.getBundle().getString("property.search.order.newer"));
        map.put("filter", messages.getBundle().getString("property.search.filter"));
        map.put("form-name", messages.getBundle().getString("property.search.form.name"));
        map.put("form-type", messages.getBundle().getString("property.search.form.type"));

        map.put("form-bath", messages.getBundle().getString("property.search.form.bath"));
        map.put("form-room", messages.getBundle().getString("property.search.form.room"));
        map.put("form-category", messages.getBundle().getString("property.search.form.category"));
        map.put("form-currency", messages.getBundle().getString("property.search.form.currency"));
        map.put("form-search", messages.getBundle().getString("property.search"));

        return Templates.search().data("map", map);
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
