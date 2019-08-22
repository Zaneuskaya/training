package com.senla.pdp.repository;

import com.senla.pdp.api.repository.GenericRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class GenericRepositoryImpl<T, K> implements GenericRepository<T, K> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> entityClass;

    protected GenericRepositoryImpl(Class<T> entityClass) {
	this.entityClass = entityClass;
    }

    public EntityManager getEntityManager() {
	return entityManager;
    }

    public Class<T> getEntityClass() {
	return entityClass;
    }
}
