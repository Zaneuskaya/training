package com.senla.pdp.api.service;

import com.senla.pdp.api.exception.CreateEntityException;
import com.senla.pdp.api.exception.DeleteEntityException;
import com.senla.pdp.api.exception.EntityNotFoundException;
import com.senla.pdp.api.exception.EntityExistsException;
import com.senla.pdp.api.exception.UpdateEntityException;

public interface GenericService<E,K> {

    E create(E entity) throws CreateEntityException, EntityExistsException;
    E getById(K id) throws EntityNotFoundException;
    E update(E t) throws UpdateEntityException;
    void delete(K id) throws DeleteEntityException;





}
