package com.senla.pdp.api.repository;

import javax.persistence.EntityManager;

public interface GenericRepository<T, K> {

    default void add(T entity) {
        getEntityManager().persist(entity);
    }

    default T get(K id) {
        return getEntityManager().find(getEntityClass(), id);
    }

    default void update(T t) {
        getEntityManager().merge(t);
    }

    default void delete(T t) {
        getEntityManager().remove(t);
    }

    EntityManager getEntityManager();

    Class<T> getEntityClass();

}
