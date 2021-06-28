package com.github.legionarks.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.github.legionarks.dao.RoleDao;
import com.github.legionarks.dao.UserDao;
import com.github.legionarks.model.role.Role;
import com.github.legionarks.model.role.RoleType;

@ApplicationScoped
public class UserService {

    @Inject
    UserDao data;

    @Inject
    RoleDao roleDao;

    public void roles() {
        Role role;

        role = new Role();
        role.setType(RoleType.ADMIN);
        roleDao.create(role);

        role = new Role();
        role.setType(RoleType.USER);
        roleDao.create(role);
    }

    /*
    public void add(String username, String password, Set<Role> roles) {
        User user = new User();

        user.setUsername(username);
        user.setPassword(BcryptUtil.bcryptHash(password));
        user.setRoles(roles);

        data.create(user);
    }

    public void add(String username, String password, List<RoleType> groups) {
        Set<Role> roles = new HashSet<>();

        groups.forEach(type -> {
            Role role;

            role = new Role();
            role.setRole(type);
            roles.add(role);
        });

        this.add(username, password, roles);
    }

    public void add(String username, String password, String groups) {
        List<RoleType> roles = Arrays.stream(groups.split(",\\s+")).map(RoleType::valueOf).collect(Collectors.toList());

        this.add(username, password, roles);
    }
    */
}
