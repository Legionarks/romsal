package com.github.legionarks.dao.property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.github.legionarks.dao.Datasource;
import com.github.legionarks.model.property.Property;

@Singleton
public class PropertyDao extends Datasource<Property> {

    public PropertyDao() {
        super(Property.class);
    }

    @Transactional
    public List<Property> find(Short page, String project, String type, String bath, String room, String category) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Property> query = builder.createQuery(clazz);
        TypedQuery<Property> enquiry;
        Root<Property> property = query.from(clazz);
        List<Predicate> predicates = new ArrayList<>();
        Predicate[] conditions = new Predicate[] {};

        predicates.add(builder.like(property.get("name"), '%' + project + '%'));

        conditions = new Predicate[predicates.size()];
        predicates.toArray(conditions);
        query.select(property);
        query.where(conditions);
        enquiry = manager.createQuery(query);

        return enquiry.getResultList();
    }

    private Map<String, Object> criteria(Short page, String project, String type, String bath, String room,
            String category) {
        final Map<String, Object> map = new HashMap<>();

        Object auxPage = page == null ? null : map.put("page", page);
        Object auxProject = project == null || project.isBlank() || project.isEmpty() ? null : map.put("NAME", project);
        Object auxType = type == null || type.isBlank() || type.isEmpty() ? null : map.put("TYPE", type);
        Object auxBath = bath == null ? null : map.put("bath", bath);
        Object auxRoom = room == null ? null : map.put("room", room);

        return map;
    }

    /*
     * predicates.add(builder.like(builder.function("convert_from", String.class,
     * builder.function("loread", Integer.class, builder.function("lo_open",
     * Integer.class, property.get("description").as(Integer.class),
     * builder.literal(0x40000)), builder.literal(0x40000)),
     * builder.literal(StandardCharsets.UTF_8.name())), '%' + description + '%'));
     */
}
