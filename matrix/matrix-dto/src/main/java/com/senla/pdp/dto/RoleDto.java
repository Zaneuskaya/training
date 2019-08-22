package com.senla.pdp.dto;

import com.senla.pdp.model.Role;

public class RoleDto extends BasicDto {

    private Integer id;
    private String role;

    public RoleDto(){}

    public RoleDto(Role role) {
	this.id = role.getId();
	this.role = role.getRole();
    }

    public Role convertDtoToEntity(){
        Role role = new Role();
        role.setId(getId());
        role.setRole(getRole());
        return role;
    }
    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }
}
