package com.github.legionarks.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

@Singleton
public abstract class Datasource<T> {

    @Inject
    protected EntityManager manager;

    protected Class<T> clazz;

    public Datasource(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Transactional
    public Object getId(T entity) {
        Object id = manager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);

        return id;
    }

    @Transactional
    public boolean create(T entity) {
        boolean ok = false;
        manager.persist(entity);
        ok = true;

        return ok;
    }

    @Transactional
    public boolean edit(T entity) {
        boolean ok = false;

        manager.merge(entity);
        ok = true;

        return ok;
    }

    @Transactional
    public boolean delete(Object id) {
        boolean ok = false;
        T entity;

        entity = manager.find(clazz, id);
        manager.remove(entity);
        ok = true;

        return ok;
    }

    @Transactional
    public T find(Object id) {
        T entity = null;

        entity = manager.find(clazz, id);

        return entity;
    }

    @Transactional
    public List<T> findAll() {
        CriteriaQuery<T> criteriaQuery;
        List<T> entities = null;

        criteriaQuery = manager.getCriteriaBuilder().createQuery(clazz);
        criteriaQuery.select(criteriaQuery.from(clazz));
        entities = manager.createQuery(criteriaQuery).getResultList();

        return entities;
    }

    public List<T> castList(Collection<?> collection) {
        List<T> list = new ArrayList<T>(collection.size());

        for (Object entity : collection) {
            list.add(clazz.cast(entity));
        }

        return list;
    }
}