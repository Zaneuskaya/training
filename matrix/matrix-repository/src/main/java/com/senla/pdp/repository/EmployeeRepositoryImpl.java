package com.senla.pdp.repository;

import com.senla.pdp.api.repository.EmployeeRepository;
import com.senla.pdp.model.*;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl extends GenericRepositoryImpl<Employee, Integer> implements EmployeeRepository {

    protected EmployeeRepositoryImpl() {
	super(Employee.class);
    }

    @Override
    public List<Employee> getEmployeesByDepartment(Department department) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Employee> employeeCriteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = employeeCriteriaQuery.from(Employee.class);
        employeeCriteriaQuery.select(employeeRoot);
        employeeCriteriaQuery.where(employeeRoot.get(Employee_.DEPARTMENT).in(department));
        employeeCriteriaQuery.orderBy(criteriaBuilder.asc(employeeRoot.get(Employee_.NAME)));
        return getEntityManager().createQuery(employeeCriteriaQuery).getResultList();
    }

    @Override
    public Employee getEmployeeByCredentials(Credentials credentials) {
	    CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
	    CriteriaQuery<Employee> employeeCriteriaQuery = criteriaBuilder.createQuery(Employee.class);
	    Root<Employee> employeeRoot = employeeCriteriaQuery.from(Employee.class);
	    employeeCriteriaQuery.select(employeeRoot);
	    employeeCriteriaQuery.where(criteriaBuilder.equal(employeeRoot.get(Employee_.CREDENTIALS),credentials));
        return getEntityManager().createQuery(employeeCriteriaQuery).getSingleResult();
    }

}
