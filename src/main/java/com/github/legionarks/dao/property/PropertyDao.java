package com.github.legionarks.dao.property;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.github.legionarks.dao.Datasource;
import com.github.legionarks.model.currency.Currency;
import com.github.legionarks.model.currency.CurrencyType;
import com.github.legionarks.model.currency.Rate;
import com.github.legionarks.model.property.Property;
import com.github.legionarks.model.property.attribute.AttributeType;
import com.github.legionarks.model.property.attribute.PropertyAttribute;

@Singleton
public class PropertyDao extends Datasource<Property> {

    public PropertyDao() {
        super(Property.class);
    }

    @Transactional
    public List<Property> find(Short size, String page, String address, String type, String room, String bath,
            String category, Currency currency, BigDecimal[] price) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Property> query = builder.createQuery(clazz);
        TypedQuery<Property> enquiry;
        Root<Property> property = query.from(clazz);
        Join<Property, PropertyAttribute> attribute = property.join("attributes", JoinType.LEFT);
        List<Predicate> conditions = new ArrayList<>();

        // Address filter
        if (address != null && !address.isBlank()) {
            conditions.add(builder.like(property.get("address"), '%' + address + '%'));
        }

        // Type filter
        if (type != null && !type.isBlank()) {
            conditions.add(builder.like(property.get("type").get("type").as(String.class), '%' + type + '%'));
        }

        // Attributes filter
        if (room != null && !room.isBlank()) {
            conditions.add(builder.and(
                    builder.like(attribute.get("attribute").get("type").as(String.class), AttributeType.ROOM.name()),
                    builder.equal(attribute.get("amount"), Short.parseShort(room))));
        }

        if (bath != null && !bath.isBlank()) {
            conditions.add(builder.and(
                    builder.like(attribute.get("attribute").get("type").as(String.class), AttributeType.BATH.name()),
                    builder.equal(attribute.get("amount"), Short.parseShort(bath))));
        }

        // Category filter
        if (category != null && !category.isBlank()) {
            conditions.add(builder.like(property.get("category").get("type").as(String.class), '%' + category + '%'));
        }

        // Price filter
        /*
        if ((currency != null && !currency.isBlank()) && price != null) {
            BigDecimal[] range = new BigDecimal[] {BigDecimal.ZERO, BigDecimal.ZERO};
            List<Predicate> prices = new ArrayList<>();
            
            for (CurrencyType money : CurrencyType.values()) {
                if (money != code) {
                    Arrays.stream(range).forEach(value -> value.multiply());
                } else {
                    range[0] = price[0];
                    range[1] = price [1];
                }

                prices.add(builder.and(builder.like(property.get("currency").get("type").as(String.class), money),
                            builder.ge(property.get("price"), price[0].multiply(rate.), builder.le(property.get("price"), dop[1])));
            }

            conditions.add(builder.or(prices.toArray(new Predicate[] {})));
        }
        */

        // Create statement
        query.where(conditions.toArray(new Predicate[] {}));
        query.select(property).distinct(true);
        enquiry = manager.createQuery(query);

        // Pagination
        if (page != null && !page.isBlank()) {
            enquiry.setFirstResult((Short.parseShort(page) - 1) * size);
            enquiry.setMaxResults(size);
        }

        return enquiry.getResultList();
    }

    public Long total() {
        return (Long) manager.createQuery("SELECT count(p.ID) from Property", Long.class).getSingleResult();
    }

    /*
     * predicates.add(builder.like(builder.function("convert_from", String.class,
     * builder.function("loread", Integer.class, builder.function("lo_open",
     * Integer.class, property.get("description").as(Integer.class),
     * builder.literal(0x40000)), builder.literal(0x40000)),
     * builder.literal(StandardCharsets.UTF_8.name())), '%' + description + '%'));
     */
}
