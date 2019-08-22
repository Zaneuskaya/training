package com.senla.pdp.repository;

import com.senla.pdp.api.repository.RoleRepository;
import com.senla.pdp.model.Role;
import com.senla.pdp.model.Role_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class RoleRepositoryImpl extends GenericRepositoryImpl<Role, Integer> implements RoleRepository {

    public RoleRepositoryImpl() {
        super(Role.class);
    }

    public List<Role> getAll() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = cb.createQuery(Role.class);
        criteriaQuery.from(Role.class);
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Role> getRolesByTitle(String title) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Role> roleCriteriaQuery = criteriaBuilder.createQuery(Role.class);
        Root<Role> roleRoot = roleCriteriaQuery.from(Role.class);
        roleCriteriaQuery.where(criteriaBuilder.equal(roleRoot.get(Role_.ROLE),title));
        return getEntityManager().createQuery(roleCriteriaQuery).getResultList();
    }

}
