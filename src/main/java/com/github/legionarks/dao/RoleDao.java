package com.github.legionarks.dao;

import javax.inject.Singleton;

import com.github.legionarks.model.user.role.Role;
import com.github.legionarks.model.user.role.RoleType;

@Singleton
public class RoleDao extends Datasource<Role> {

    public RoleDao() {
        super(Role.class);
    }

    public Role find(RoleType type) {
        return manager.createQuery("FROM Role WHERE type = :type", clazz).setParameter("type", type.name()).getSingleResult();
    }
}
