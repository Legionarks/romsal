package com.github.legionarks.dao;

import javax.inject.Singleton;

import com.github.legionarks.model.Contact;

@Singleton
public class ContactDao extends Datasource<Contact> {

    public ContactDao() {
        super(Contact.class);
    }
}
