package com.github.legionarks.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.github.legionarks.dao.RoleDao;
import com.github.legionarks.dao.UserDao;
import com.github.legionarks.model.user.User;
import com.github.legionarks.model.user.role.Role;
import com.github.legionarks.model.user.role.RoleType;

import io.quarkus.elytron.security.common.BcryptUtil;

@ApplicationScoped
public class UserService {

    @Inject
    UserDao data;

    @Inject
    RoleDao roleDao;

    public void roles() {
        for (RoleType role : RoleType.values()) {
            roleDao.create(new Role(role));
        }
    }

    public void add(String username, String password, Set<Role> roles) {
        User user = new User();

        user.setUsername(username);
        user.setPassword(BcryptUtil.bcryptHash(password));
        user.setRoles(roles);

        data.create(user);
    }

    public void add(String username, String password, List<RoleType> groups) {
        Set<Role> roles = new HashSet<>();

        groups.forEach(type -> roles.add(roleDao.find(type)));

        add(username, password, roles);
    }

    public void add(String username, String password, String groups) {
        List<RoleType> roles = Arrays.stream(groups.split(",\\s+")).map(RoleType::valueOf).collect(Collectors.toList());

        add(username, password, roles);
    }

}
