package com.senla.pdp.api.service;

import com.senla.pdp.api.exception.EntityNotFoundException;
import com.senla.pdp.model.Employee;

public interface EmployeeService extends GenericService<Employee, Integer> {

    Employee getEmployeeByCredentials (String email, String password) throws EntityNotFoundException;




}
