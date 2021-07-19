package com.github.legionarks.dao.property;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.github.legionarks.model.property.Property;
import com.github.legionarks.model.property.attribute.AttributeType;
import com.github.legionarks.model.property.attribute.PropertyAttribute;

@Singleton
public class PropertyDao extends Datasource<Property> {

    public PropertyDao() {
        super(Property.class);
    }

    @Transactional
    public List<Property> find(String page, String address, String type, String room, String bath, String category,
            String currency, BigDecimal[] price) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Property> query = builder.createQuery(clazz);
        TypedQuery<Property> enquiry;
        Root<Property> property = query.from(clazz);
        Join<Property, PropertyAttribute> attribute = property.join("attributes", JoinType.LEFT);
        List<Predicate> conditions = new ArrayList<>();
        Predicate[] wheres;

        if (page != null && !page.isBlank()) {
            try {
                Short p = Short.parseShort(page);
            } catch (NumberFormatException e) {
                page = null;
            }
        }

        conditions.add(builder.like(property.get("address"), '%' + address + '%'));
        conditions.add(builder.like(property.get("type").get("type").as(String.class), '%' + type + '%'));
        conditions.add(builder.and(
                builder.like(attribute.get("attribute").get("type").as(String.class), AttributeType.ROOM.name()),
                builder.equal(attribute.get("amount"), Short.parseShort(room))));
        conditions.add(builder.and(
                builder.like(attribute.get("attribute").get("type").as(String.class), AttributeType.BATH.name()),
                builder.equal(attribute.get("amount"), Short.parseShort(bath))));
        conditions.add(builder.like(property.get("category").get("type").as(String.class), '%' + category + '%'));

        wheres = new Predicate[conditions.size()];
        conditions.toArray(wheres);
        query.where(wheres);
        query.select(property);
        enquiry = manager.createQuery(query);

        return enquiry.getResultList();
    }

    private Long pages() {
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
