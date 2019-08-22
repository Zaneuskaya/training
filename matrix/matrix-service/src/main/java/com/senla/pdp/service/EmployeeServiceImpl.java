package com.senla.pdp.service;

import com.senla.pdp.api.exception.CreateEntityException;
import com.senla.pdp.api.exception.DeleteEntityException;
import com.senla.pdp.api.exception.EntityExistsException;
import com.senla.pdp.api.exception.EntityNotFoundException;
import com.senla.pdp.api.exception.UpdateEntityException;
import com.senla.pdp.api.repository.CredentialsRepository;
import com.senla.pdp.api.repository.EmployeeRepository;
import com.senla.pdp.api.service.EmployeeService;
import com.senla.pdp.model.Credentials;
import com.senla.pdp.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Override
    public Employee getEmployeeByCredentials(String email, String password) throws EntityNotFoundException {
        LOGGER.debug("Get credentials of {}", email);
        Credentials credentials = credentialsRepository.findCredentialsByEmail(email);
        if (credentials == null || !credentials.getPassword().equals(password)) {
            throw new EntityNotFoundException("Credentials were not found");
        }
        LOGGER.debug("Getting employee with credentials {}", email);
        Employee employee;
        try {
            employee = employeeRepository.getEmployeeByCredentials(credentials);
        } catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
        return employee;
    }

    @Override
    public Employee create(Employee entity) throws CreateEntityException {
        LOGGER.debug("Create new employee {}", entity.getName());
        try {
            if(employeeRepository.getEmployeeByCredentials(entity.getCredentials()) != null){
                throw new EntityExistsException("Employee already linked to login-pass");
            }
            employeeRepository.add(entity);
        } catch (Exception e) {
            throw new CreateEntityException(e.getMessage());
        }
        return entity;
    }

    @Override
    public Employee getById(Integer id) throws EntityNotFoundException {
        LOGGER.debug("Get employee by id {}", id);
        Employee employee = employeeRepository.get(id);
        if (employee == null){
            throw new EntityNotFoundException("Employee is not found");
        }
        return employee;
    }

    @Override
    public Employee update(Employee employee) throws UpdateEntityException {
        LOGGER.debug("Update employee {}", employee.getName());
        try {
            employeeRepository.update(employee);
        } catch (Exception e) {
            throw new UpdateEntityException(e.getMessage());
        }
        return employee;
    }

    @Override
    public void delete(Integer id) throws DeleteEntityException {
        LOGGER.debug("Delete employee with id{}", id);
        try {
            Employee employee = employeeRepository.get(id);
            employeeRepository.delete(employee);
        } catch (Exception e) {
            throw new DeleteEntityException(e.getMessage());
        }
    }
}
