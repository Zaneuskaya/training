package com.senla.pdp.service;

import com.senla.pdp.api.exception.CreateEntityException;
import com.senla.pdp.api.exception.DeleteEntityException;
import com.senla.pdp.api.exception.EntityExistsException;
import com.senla.pdp.api.exception.EntityNotFoundException;
import com.senla.pdp.api.exception.UpdateEntityException;
import com.senla.pdp.api.repository.DepartmentRepository;
import com.senla.pdp.api.repository.OfficeRepository;
import com.senla.pdp.api.service.DepartmentService;
import com.senla.pdp.model.Department;
import com.senla.pdp.model.Office;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private OfficeRepository officeRepository;

    @Override
    public List<Department> getDepartmentsByOffice(Integer officeId) throws EntityNotFoundException {
        LOGGER.debug("Find office by id {}", officeId);
        Office office = officeRepository.get(officeId);
        if (office == null) {
            throw new EntityNotFoundException("Office not found");
        }
        LOGGER.debug("Get departments by office {} at {},{}", office.getId(), office.getCountry(), office.getCity());
        List<Department> departments = departmentRepository.getDepartmentsByOffice(office);
        LOGGER.debug("Office {} contains {} departments", office.getId(), departments.size());
        return departments;
    }

    @Override
    public List<Department> getTopDepartments() throws EntityNotFoundException {
        LOGGER.debug("Get departments without senior department");
        List<Department> departmentList = new ArrayList<>();
        try {
            departmentList = departmentRepository.getDepartmentsBySeniorDepartment(null);
        }
        catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
        LOGGER.debug("Top departments list contains {} departments", departmentList.size());
        return departmentList;
    }

    @Override
    public List<Department> getSubDepartments(Integer id) throws EntityNotFoundException {
        LOGGER.debug("Get subdepartments of department{}", id);
        List<Department> departmentList = new ArrayList<>();
        try {
            Department department = departmentRepository.get(id);
            departmentList = departmentRepository.getDepartmentsBySeniorDepartment(department);
        }
        catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
        LOGGER.debug("Department {} contains {} departments", id, departmentList.size());
        return departmentList;
    }

    @Override
    public Department create(Department entity) throws CreateEntityException {
        LOGGER.debug("Creating new department {}", entity.getTitle());
        try {
            if (entity.getOffice() == null) {
                throw new CreateEntityException("Department can't be created as office is not provided");
            }
            if (!departmentRepository.getDepartmentByTitleAndOffice(entity.getTitle(), entity.getOffice()).isEmpty()) {
                throw new EntityExistsException("Department already exists");
            }
            departmentRepository.add(entity);
        }
        catch (Exception e) {
            throw new CreateEntityException(e.getMessage());
        }
        return entity;
    }

    @Override
    public Department getById(Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting department by id {}", id);
        Department department;
        try {
            department = departmentRepository.get(id);
        }
        catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
        return department;
    }

    @Override
    public Department update(Department department) throws UpdateEntityException {
        LOGGER.debug("Update department {}", department.getTitle());
        try {
            departmentRepository.update(department);
        }
        catch (Exception e) {
            throw new UpdateEntityException(e.getMessage());
        }
        return department;
    }

    @Override
    public void delete(Integer id) throws DeleteEntityException {
        LOGGER.debug("Delete department with id {}", id);
        try {
            Department department = departmentRepository.get(id);
            departmentRepository.delete(department);
        }
        catch (Exception e) {
            throw new DeleteEntityException(e.getMessage());
        }
    }
}
