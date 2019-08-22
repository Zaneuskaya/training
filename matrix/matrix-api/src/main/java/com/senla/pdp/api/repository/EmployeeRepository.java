package com.senla.pdp.api.repository;

import com.senla.pdp.model.Department;
import com.senla.pdp.model.Employee;
import com.senla.pdp.model.Credentials;
import java.util.List;

public interface EmployeeRepository extends  GenericRepository<Employee, Integer> {

    List<Employee> getEmployeesByDepartment(Department department);
    Employee getEmployeeByCredentials(Credentials credentials);

}
