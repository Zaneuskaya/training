package com.senla.pdp.controller;

import com.senla.pdp.api.exception.CreateEntityException;
import com.senla.pdp.api.exception.DeleteEntityException;
import com.senla.pdp.api.exception.EntityExistsException;
import com.senla.pdp.api.exception.EntityNotFoundException;
import com.senla.pdp.api.exception.UpdateEntityException;
import com.senla.pdp.api.service.RoleService;
import com.senla.pdp.dto.RoleDto;
import com.senla.pdp.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public List<RoleDto> getRoles() {
        List<RoleDto> roles = roleService.getAll().stream().map(RoleDto::new).collect(Collectors.toList());
        return roles;
    }

    @GetMapping("/{id}")
    public Role getRole(@PathVariable Integer id) throws EntityNotFoundException {
        return roleService.getById(id);
    }

    @PostMapping("/add")
    public RoleDto createRole(@RequestBody RoleDto role)
            throws CreateEntityException, EntityNotFoundException, EntityExistsException {
        return new RoleDto(roleService.create(role.convertDtoToEntity()));
    }

    @PutMapping("/edit")
    public RoleDto editRole(@RequestBody RoleDto role) throws UpdateEntityException {
        return new RoleDto(roleService.update(role.convertDtoToEntity()));
    }

    @DeleteMapping("/delete/{id}")
    public void removeRole(@PathVariable Integer id) throws EntityNotFoundException, DeleteEntityException {
        roleService.delete(id);
    }

}
