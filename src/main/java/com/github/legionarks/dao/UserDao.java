package com.github.legionarks.dao;

import javax.inject.Singleton;

import com.github.legionarks.model.User;

@Singleton
public class UserDao extends Datasource<User> {

    public UserDao() {
        super(User.class);
    }
}
