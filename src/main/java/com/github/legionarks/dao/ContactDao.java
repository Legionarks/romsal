package com.github.legionarks.dao;

import java.util.List;

import javax.inject.Singleton;

import com.github.legionarks.model.Contact;
import com.github.legionarks.model.Location;

@Singleton
public class ContactDao extends Datasource<Contact> {

    public ContactDao() {
        super(Contact.class);
    }

    public List<Location> locations() {
        return manager.createQuery("SELECT location FROM Contact", Location.class).getResultList();
    }
}
