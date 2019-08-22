package com.senla.pdp.api.repository;

import com.senla.pdp.api.exception.EntityNotFoundException;
import com.senla.pdp.model.Credentials;

import java.util.List;

public interface CredentialsRepository extends GenericRepository<Credentials, Integer> {

    List<Credentials> getAll();
    Credentials findCredentialsByEmail(String email);
}
