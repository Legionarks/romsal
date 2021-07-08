package com.github.legionarks.dao.property;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import com.github.legionarks.dao.Datasource;
import com.github.legionarks.model.property.Property;

@Singleton
public class PropertyDao extends Datasource<Property> {

    public PropertyDao() {
        super(Property.class);
    }

    public List<Property> find(Map<String, Object> criteria) {
        return null; // manager.createQuery("FROM Property WHERE TYPE = :name", Property.class).getResultList();
    }

    private Map<String, Object> criteria(Short page, String project, String type, String bath, String room, String category) {
        final Map<String, Object> map = new HashMap<>();

        Object auxPage = page == null ? null : map.put("page", page);
        Object auxProject = project == null || project.isBlank() || project.isEmpty() ? null : map.put("NAME", project);
        Object auxType = type == null || type.isBlank() || type.isEmpty() ? null : map.put("TYPE", type);
        Object auxBath = bath == null ? null : map.put("bath", bath);
        Object auxRoom = room == null ? null : map.put("room", room);

        return map;
    }

    private String condition(String column, String value) {
        return column + " = " + value;
    }
}
