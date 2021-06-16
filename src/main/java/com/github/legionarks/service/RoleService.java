package com.github.legionarks.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.github.legionarks.dao.RoleDao;
import com.github.legionarks.model.role.Role;
import com.github.legionarks.model.role.RoleType;

@ApplicationScoped
public class RoleService {

    @Inject
    RoleDao data = new RoleDao();

    public void add(RoleType group) {
        Role role = new Role();

        role.setRole(group.name());

        data.create(role);
    }
}
