package com.github.legionarks.util;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.github.legionarks.model.property.category.CategoryType;
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

    public Bootstrap() {
    }

    @PostConstruct
    public void init() {
        properties();
        users();
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
}
