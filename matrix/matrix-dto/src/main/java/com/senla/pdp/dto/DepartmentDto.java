package com.senla.pdp.dto;

import com.senla.pdp.model.Department;
import com.senla.pdp.model.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentDto extends BasicDto {

    private Integer id;
    private String title;
    private Integer office;
    private List<Integer> employees;
    private Integer headOfDepartment;
    private Integer seniorDepartment;

    public DepartmentDto() {}

    public DepartmentDto(Department department) {
        this.id = department.getId();
        this.title = department.getTitle();
        this.office = department.getOffice().getId();
        this.employees = department.getEmployees().stream().map(Employee::getId).collect(Collectors.toList());
        this.headOfDepartment = department.getDepartmentHead() != null ? department.getDepartmentHead().getId() : null;
        this.seniorDepartment = department.getSeniorDepartment() != null ? department.getSeniorDepartment().getId() :
                                null;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOffice() {
        return office;
    }

    public void setOffice(Integer office) {
        this.office = office;
    }

    public List<Integer> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Integer> employees) {
        this.employees = employees;
    }

    public Integer getHeadOfDepartment() {
        return headOfDepartment;
    }

    public void setHeadOfDepartment(Integer headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }

    public Integer getSeniorDepartment() {
        return seniorDepartment;
    }

    public void setSeniorDepartment(Integer seniorDepartment) {
        this.seniorDepartment = seniorDepartment;
    }

    public Department convertDtoToEntity() {
        Department department = new Department();
        department.setId(getId());
        department.setTitle(getTitle());
        return department;
    }

}
