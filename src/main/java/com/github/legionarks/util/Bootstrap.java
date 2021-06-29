package com.github.legionarks.util;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.github.legionarks.model.Contact;
import com.github.legionarks.model.property.category.CategoryType;
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
        propertyService.categories();
        
        propertyService.add("Casa de bibi", "La casa de la loqita fanatica de los juegos y qsy...",
                BigDecimal.valueOf(15000), CategoryType.RENT, true);
        propertyService.add("Casa de Mich", "La caqsy...", BigDecimal.valueOf(1500), CategoryType.SELL, false);
        propertyService.add("Casa de John", "La caqsy...", BigDecimal.valueOf(1500), CategoryType.RENT, true);
    }

    public void users() {
        userService.roles();

        // userService.add("user", "user", "USER");
        // userService.add("admin", "admin", "ADMIN");
    }

    private void contact() {
        Contact contact;

        contact = new Contact();
        contact.setCity("Santiago de los Caballeros");
        contact.setAddress("C/ Parada Vieja, Res. María Alejandra I 2do Nivel");
        contact.setPhone("(809) 820 - 0897");
        contactService.getData().create(contact);
    }
}
