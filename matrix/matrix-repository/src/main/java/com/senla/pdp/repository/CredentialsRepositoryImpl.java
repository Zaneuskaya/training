package com.senla.pdp.repository;

import com.senla.pdp.api.repository.CredentialsRepository;
import com.senla.pdp.model.Credentials;
import com.senla.pdp.model.Credentials_;
import org.springframework.stereotype.Repository;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CredentialsRepositoryImpl extends GenericRepositoryImpl<Credentials, Integer> implements
        CredentialsRepository {

    protected CredentialsRepositoryImpl() {
        super(Credentials.class);
    }

    @Override
    public List<Credentials> getAll() {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Credentials> credentialsCriteriaQuery = criteriaBuilder.createQuery(Credentials.class);
        credentialsCriteriaQuery.from(Credentials.class);
        return getEntityManager().createQuery(credentialsCriteriaQuery).getResultList();
    }

    @Override
    public Credentials findCredentialsByEmail(String email) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Credentials> credentialsCriteriaQuery = criteriaBuilder.createQuery(Credentials.class);
        Root<Credentials> credentialsRoot = credentialsCriteriaQuery.from(Credentials.class);
        credentialsCriteriaQuery.select(credentialsRoot);
        credentialsCriteriaQuery.where(criteriaBuilder.equal(credentialsRoot.get(Credentials_.MAIL), email));
        return getEntityManager().createQuery(credentialsCriteriaQuery).getSingleResult();
    }
}
