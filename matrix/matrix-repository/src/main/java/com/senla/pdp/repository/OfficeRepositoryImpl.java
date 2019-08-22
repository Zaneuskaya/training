package com.senla.pdp.repository;

import com.senla.pdp.api.repository.OfficeRepository;
import com.senla.pdp.model.Office;
import com.senla.pdp.model.Office_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository public class OfficeRepositoryImpl extends GenericRepositoryImpl<Office, Integer>
        implements OfficeRepository {

    protected OfficeRepositoryImpl() {
        super(Office.class);
    }

    @Override public Office getOfficeByCountryCityAddress(String country, String city, String address) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Office> officeCriteriaQuery = criteriaBuilder.createQuery(Office.class);
        Root<Office> officeRoot = officeCriteriaQuery.from(Office.class); officeCriteriaQuery
                .where(criteriaBuilder.like(officeRoot.get(Office_.COUNTRY), country),
                        criteriaBuilder.like(officeRoot.get(Office_.CITY), city),
                        criteriaBuilder.like(officeRoot.get(Office_.ADDRESS), address));
        return getEntityManager().createQuery(officeCriteriaQuery).getSingleResult();
    }
}
