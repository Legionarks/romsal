package com.github.legionarks.controller;

import java.math.BigDecimal;
import java.util.Arrays;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.github.legionarks.model.property.Property;
import com.github.legionarks.service.CurrencyService;
import com.github.legionarks.service.PropertyService;
import com.github.legionarks.util.Templates;

import io.quarkus.qute.TemplateInstance;

@Path("property")
public class PropertyResource {

    @Inject
    PropertyService service;

    @Inject
    CurrencyService currencyService;

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

        transcript.getMap().put("page", "property");
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
    public TemplateInstance search(@QueryParam("page") String page, @QueryParam("address") String address,
            @QueryParam("type") String type, @QueryParam("room") String room, @QueryParam("bath") String bath,
            @QueryParam("category") String category, @QueryParam("currency") String currency,
            @QueryParam("price-min") BigDecimal min, @QueryParam("price-max") BigDecimal max) {
        final Transcript transcript = new Transcript();
        final Short size = 10;

        System.out.println("INI");
        System.out.println("page:" + page);
        System.out.println("address:" + address);
        System.out.println("type:" + type);
        System.out.println("room:" + room);
        System.out.println("bath:" + bath);
        System.out.println("category:" + category);
        System.out.println("currency:"+currency);
        System.out.println("min:" + min);
        System.out.println("max:" + max);

        service.find(size, page, address, type, bath, room, category, currency, new BigDecimal[]{min, max}).forEach(property -> System.out.println(property.getName()));
        System.out.println("FIN");

        transcript.getMap().put("page", "property");
        transcript.defaults();

        transcript.put("orders", "property.search.order.newer");
        transcript.put("filter", "property.search.order.default");
        transcript.put("filter-list", Arrays.asList(Order.values()).stream()
                .map(order -> transcript.getMessages().getBundle().getString(order.getProperty())));
        transcript.put("form-name", "property.search.form.name");
        transcript.put("form-type", "property.search.form.type");

        transcript.put("form-bath", "property.search.form.bath");
        transcript.put("form-room", "property.search.form.room");
        transcript.put("form-category", "property.search.form.category");
        transcript.put("form-currency", "property.search.form.currency");
        transcript.put("form-search", "property.search");

        transcript.put("pagination", 1);

        transcript.put("properties", service.find(size, page, address, type, bath, room, category, currency, new BigDecimal[]{min, max}));

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
        DEFAULT("property.search.order.default"), NEWER("property.search.order.newer"),
        OLDER("property.search.order.older"), PRICE_LOW("property.search.order.price.low"),
        PRICE_HIGH("property.search.order.price.high"), OUTSTANDING("property.search.order.outstanding");

        private final String property;

        private Order(String property) {
            this.property = property;
        }

        public String getProperty() {
            return property;
        }
    }
}
