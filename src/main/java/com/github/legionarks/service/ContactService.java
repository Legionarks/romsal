package com.github.legionarks.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.github.legionarks.dao.ContactDao;

@ApplicationScoped
public class ContactService {

    @Inject
    ContactDao data;

    public ContactDao getData() {
        return data;
    }
}
