package com.github.legionarks.util;

import java.math.BigDecimal;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.github.legionarks.model.Contact;
import com.github.legionarks.model.Location;
import com.github.legionarks.model.property.Media;
import com.github.legionarks.model.property.Property;
import com.github.legionarks.model.property.attribute.AttributeType;
import com.github.legionarks.model.property.attribute.PropertyAttribute;
import com.github.legionarks.model.property.category.CategoryType;
import com.github.legionarks.model.property.feature.FeatureType;
import com.github.legionarks.model.property.type.PropertyType;
import com.github.legionarks.service.ContactService;
import com.github.legionarks.service.PropertyService;
import com.github.legionarks.service.UserService;

import io.quarkus.runtime.Startup;

@Startup
@Singleton
public class Bootstrap {

    @Inject
    PropertyService propertyService;

    @Inject
    UserService userService;

    @Inject
    ContactService contactService;

    public Bootstrap() {
    }

    @PostConstruct
    public void init() {
        properties();
        users();
        contact();
    }

    private void properties() {
        Property property;

        propertyService.types();
        propertyService.categories();
        propertyService.attributes();
        propertyService.features();

        property = new Property();
        property.setName("Penthouse of your dreams");
        property.setDescription(
                "The property is fully occupied, with a 16.1 gross rent multiplier and a 4.11% cap rate. These details are critical when trying to generate new leads among real estate investors.");
        property.setAddress("Santiago de los Caballeros");
        property.setPrice(BigDecimal.valueOf(16450.50));
        property.setOutstanding(true);
        property.setSummary(". . .");
        property.setType(propertyService.getTypeDao().find(PropertyType.HOUSE.name()));
        property.setCategory(propertyService.getCategoryDao().find(CategoryType.SELL.name()));
        property.setLocation(new Location(18.500000F, -69.983300F));
        property.setAttributes(Set.of(
                new PropertyAttribute(property, propertyService.getAttributeDao().find(AttributeType.ROOM.name()),
                        (short) 5),
                new PropertyAttribute(property, propertyService.getAttributeDao().find(AttributeType.BATH.name()),
                        (short) 7),
                new PropertyAttribute(property, propertyService.getAttributeDao().find(AttributeType.PARKING.name()),
                        (short) 2),
                new PropertyAttribute(property, propertyService.getAttributeDao().find(AttributeType.AREA.name()),
                        (short) 208)));
        property.setFeatures(Set.of(propertyService.getFeatureDao().find(FeatureType.POOL.name()),
                propertyService.getFeatureDao().find(FeatureType.ALARM.name())));
        property.setMedias(Set.of(new Media(property, "inside-01"), new Media(property, "inside-02")));
        propertyService.getPropertyDao().create(property);
    }

    public void users() {
        userService.roles();

        // userService.add("user", "user", "USER");
        // userService.add("admin", "admin", "ADMIN");
    }

    private void contact() {
        Contact contact;
        Location location;

        contact = new Contact();
        location = new Location();
        contact.setCity("Santiago de los Caballeros");
        contact.setAddress("C/ Parada Vieja, Res. María Alejandra I 2do Nivel");
        contact.setPhone("(809) 820 - 0897");
        location.set(18.500000F, -69.983300F);
        contact.setLocation(location);
        contactService.getData().create(contact);

        contact = new Contact();
        location = new Location();
        contact.setCity("Santo Domingo");
        contact.setAddress("Av. Independencia");
        contact.setPhone("(809) 580 - 1111");
        location.set(18.500000F, -69.983300F);
        contact.setLocation(location);
        contactService.getData().create(contact);
    }
}
