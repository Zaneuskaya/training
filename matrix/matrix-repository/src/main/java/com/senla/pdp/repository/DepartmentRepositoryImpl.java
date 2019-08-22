package com.senla.pdp.repository;

import com.senla.pdp.api.repository.DepartmentRepository;
import com.senla.pdp.model.Department;
import com.senla.pdp.model.Department_;
import com.senla.pdp.model.Office;
import com.senla.pdp.model.Office_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class DepartmentRepositoryImpl extends GenericRepositoryImpl<Department, Integer>
        implements DepartmentRepository {

    protected DepartmentRepositoryImpl() {
        super(Department.class);
    }

    @Override
    public List<Department> getDepartmentsByOffice(Office office) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Department> criteriaQuery = criteriaBuilder.createQuery(Department.class);
        Root<Department> departmentRoot = criteriaQuery.from(Department.class);
        criteriaQuery.select(departmentRoot);
        Join<Department, Office> departmentOffice = departmentRoot.join(Department_.office);
        criteriaQuery.where(criteriaBuilder.equal(departmentOffice.get(Office_.id), office.getId()));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Department> getDepartmentsBySeniorDepartment(Department department) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Department> criteriaQuery = criteriaBuilder.createQuery(Department.class);
        Root<Department> departmentRoot = criteriaQuery.from(Department.class);
        criteriaQuery.select(departmentRoot);
        criteriaQuery.where(criteriaBuilder.equal(departmentRoot.get(Department_.SENIOR_DEPARTMENT), department));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Department> getDepartmentByTitleAndOffice(String title, Office office) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Department> departmentCriteriaQuery = criteriaBuilder.createQuery(Department.class);
        Root<Department> departmentRoot = departmentCriteriaQuery.from(Department.class);
        Join<Department, Office> departmentOffice = departmentRoot.join(Department_.office);
        departmentCriteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(departmentOffice.get(Office_.id),
                office.getId())),criteriaBuilder.equal(departmentRoot.get(Department_.TITLE),title));
        return getEntityManager().createQuery(departmentCriteriaQuery).getResultList();
    }

}
