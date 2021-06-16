package com.github.legionarks.dao;

import javax.inject.Singleton;

import com.github.legionarks.model.role.Role;

@Singleton
public class RoleDao extends Datasource<Role> {

    public RoleDao() {
        super(Role.class);
    }
}
