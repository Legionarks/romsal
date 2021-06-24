package com.github.legionarks.util;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.github.legionarks.model.role.RoleType;
import com.github.legionarks.service.PropertyService;
import com.github.legionarks.service.RoleService;
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
    RoleService roleService;

    public Bootstrap() {
    }

    @PostConstruct
    public void init() {
        loadProperties();
        loadRoles();
        //loadUsers();
    }

    private void loadProperties() {
        propertyService.add("Casa de bibi", "La casa de la loqita fanatica de los juegos y qsy...", BigDecimal.valueOf(15000), null, true);
    }

    public void loadRoles() {
        roleService.add(RoleType.ADMIN);
        roleService.add(RoleType.USER);
    }

    public void loadUsers() {
        userService.add("user", "user", "USER");
        userService.add("admin", "admin", "ADMIN");
    }
}
