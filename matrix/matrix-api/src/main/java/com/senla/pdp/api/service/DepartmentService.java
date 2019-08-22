package com.senla.pdp.api.service;

import com.senla.pdp.api.exception.EntityNotFoundException;
import com.senla.pdp.model.Department;

import java.util.List;

public interface DepartmentService extends GenericService<Department, Integer> {

    List<Department> getDepartmentsByOffice(Integer officeId) throws EntityNotFoundException;

    List<Department> getTopDepartments() throws EntityNotFoundException;

    List<Department> getSubDepartments(Integer departmentId) throws EntityNotFoundException;

}
