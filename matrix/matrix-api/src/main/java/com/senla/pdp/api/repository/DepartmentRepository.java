package com.senla.pdp.api.repository;


import com.senla.pdp.model.Department;
import com.senla.pdp.model.Employee;
import com.senla.pdp.model.Office;
import java.util.List;

public interface DepartmentRepository extends  GenericRepository<Department, Integer> {

    List<Department> getDepartmentsByOffice(Office office);

    List<Department> getDepartmentsBySeniorDepartment(Department department);

    List<Department> getDepartmentByTitleAndOffice(String title, Office office);

}
