package com.senla.pdp.controller;

import com.senla.pdp.api.exception.CreateEntityException;
import com.senla.pdp.api.exception.DeleteEntityException;
import com.senla.pdp.api.exception.EntityExistsException;
import com.senla.pdp.api.exception.EntityNotFoundException;
import com.senla.pdp.api.exception.UpdateEntityException;
import com.senla.pdp.api.service.DepartmentService;
import com.senla.pdp.api.service.EmployeeService;
import com.senla.pdp.api.service.OfficeService;
import com.senla.pdp.dto.DepartmentDto;
import com.senla.pdp.model.Department;
import com.senla.pdp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private OfficeService officeService;

    @GetMapping("/office/{officeId}")
    public List<DepartmentDto> getDepartmentsByOffice(
            @PathVariable
                    Integer officeId) throws EntityNotFoundException {
        List<Department> departments = departmentService.getDepartmentsByOffice(officeId);
        List<DepartmentDto> departmentDtos = departments.stream().map(DepartmentDto::new).collect(Collectors.toList());
        return departmentDtos;
    }

    @GetMapping("/top")
    public List<DepartmentDto> getTopDepartments() throws EntityNotFoundException {
        List<Department> departments = departmentService.getTopDepartments();
        return departments.stream().map(DepartmentDto::new).collect(Collectors.toList());
    }

    @GetMapping("/sub/{id}")
    public List<DepartmentDto> getSubdepartments(
            @PathVariable
                    Integer id) throws EntityNotFoundException {
        List<Department> departments = departmentService.getTopDepartments();
        return departments.stream().map(DepartmentDto::new).collect(Collectors.toList());
    }

    @PostMapping("/create")
    public DepartmentDto create(
            @RequestBody
                    DepartmentDto departmentDto)
            throws EntityNotFoundException, EntityExistsException, CreateEntityException {
        Department department = departmentDto.convertDtoToEntity();
        Employee headOfDept =
                departmentDto.getHeadOfDepartment() != null ?
                employeeService.getById(departmentDto.getHeadOfDepartment()) : null;
        department.setDepartmentHead(headOfDept);
        Department senDept =
                departmentDto.getSeniorDepartment() != null ?
                departmentService.getById(departmentDto.getSeniorDepartment()) : null;
        department.setSeniorDepartment(senDept);
        List<Employee> employees = new ArrayList<>();
        if (!CollectionUtils.isEmpty(departmentDto.getEmployees())) {
            for (Integer empId : departmentDto.getEmployees()) {
                employees.add(employeeService.getById(empId));
            }
        }
        department.setEmployees(employees);
        department.setOffice(officeService.getById(departmentDto.getOffice()));
        departmentService.create(department);
        return new DepartmentDto(department);
    }

    @GetMapping("/{id}")
    public DepartmentDto getDepartment(@PathVariable Integer id) throws EntityNotFoundException {
        return new DepartmentDto(departmentService.getById(id));
    }

    @PutMapping("/update")
    public DepartmentDto updateDepartment(
            @RequestBody
                    DepartmentDto departmentDto)
            throws EntityNotFoundException, UpdateEntityException {
        Department department = departmentDto.convertDtoToEntity();
        Employee headOfDept =
                departmentDto.getHeadOfDepartment() != null ?
                employeeService.getById(departmentDto.getHeadOfDepartment()) : null;
        department.setDepartmentHead(headOfDept);
        Department senDept =
                departmentDto.getSeniorDepartment() != null ?
                departmentService.getById(departmentDto.getSeniorDepartment()) : null;
        department.setSeniorDepartment(senDept);
        List<Employee> employees = new ArrayList<>();
        if (!CollectionUtils.isEmpty(departmentDto.getEmployees())) {
            for (Integer empId : departmentDto.getEmployees()) {
                employees.add(employeeService.getById(empId));
            }
        }
        department.setEmployees(employees);
        department.setOffice(officeService.getById(departmentDto.getOffice()));
        return new DepartmentDto(departmentService.update(department));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) throws DeleteEntityException {
        departmentService.delete(id);
    }

}
