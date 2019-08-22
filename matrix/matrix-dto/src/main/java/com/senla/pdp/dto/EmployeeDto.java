package com.senla.pdp.dto;

import com.senla.pdp.model.Employee;
import com.senla.pdp.model.Skill;
import com.senla.pdp.model.SkillEmployee;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDto extends BasicDto {

    private Integer id;
    private LocalDate birthday;
    private String name;
    private DepartmentSimpleDto department;
    private PositionSimpleDto position;
    private List<Integer> skills;
    private Integer credentials;
    private PhotoDto photo;

    public EmployeeDto(){}

    public EmployeeDto(Employee employee) {
	this.id = employee.getId();
	this.birthday = employee.getBirthday();
	this.name = employee.getName();
	this.department = new DepartmentSimpleDto(employee.getDepartment());
	this.position = new PositionSimpleDto(employee.getPosition());
	this.skills = employee.getSkills().stream().map(SkillEmployee::getSkill).map(Skill::getId)
			.collect(Collectors.toList());
	this.credentials = employee.getCredentials().getId();
	this.photo = new PhotoDto(employee.getPhoto());
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public LocalDate getBirthday() {
	return birthday;
    }

    public void setBirthday(LocalDate birthday) {
	this.birthday = birthday;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public DepartmentSimpleDto getDepartment() {
	return department;
    }

    public void setDepartment(DepartmentSimpleDto department) {
	this.department = department;
    }

    public PositionSimpleDto getPosition() {
	return position;
    }

    public void setPosition(PositionSimpleDto position) {
	this.position = position;
    }

    public List<Integer> getSkills() {
	return skills;
    }

    public void setSkills(List<Integer> skills) {
	this.skills = skills;
    }

    public Integer getCredentials() {
	return credentials;
    }

    public void setCredentials(Integer credentials) {
	this.credentials = credentials;
    }

    public PhotoDto getPhoto() {
	return photo;
    }

    public void setPhoto(PhotoDto photo) {
	this.photo = photo;
    }
}
