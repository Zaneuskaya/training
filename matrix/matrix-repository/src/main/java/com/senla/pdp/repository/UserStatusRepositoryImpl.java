package com.senla.pdp.repository;

import com.senla.pdp.api.exception.EntityNotFoundException;
import com.senla.pdp.api.repository.UserStatusRepository;
import com.senla.pdp.model.UserStatus;
import com.senla.pdp.model.UserStatus_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class UserStatusRepositoryImpl extends GenericRepositoryImpl<UserStatus, Integer>
        implements UserStatusRepository {

    protected UserStatusRepositoryImpl() {
        super(UserStatus.class);
    }

    @Override
    public UserStatus getUserStatusByTitle(String title) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserStatus> userStatusCriteriaQuery = criteriaBuilder.createQuery(UserStatus.class);
        Root<UserStatus> userStatusRoot = userStatusCriteriaQuery.from(UserStatus.class);
        userStatusCriteriaQuery.where(criteriaBuilder.like(userStatusRoot.get(UserStatus_.TITLE), title));
        return getEntityManager().createQuery(userStatusCriteriaQuery).getSingleResult();
    }
}
