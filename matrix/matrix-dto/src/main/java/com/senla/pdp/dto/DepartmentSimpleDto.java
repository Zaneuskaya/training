package com.senla.pdp.dto;

import com.senla.pdp.model.Department;

public class DepartmentSimpleDto {

    private Integer id;
    private String title;

    public DepartmentSimpleDto(){}

    public DepartmentSimpleDto(Department department) {
        this.id = department.getId();
        this.title = department.getTitle();
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
}
