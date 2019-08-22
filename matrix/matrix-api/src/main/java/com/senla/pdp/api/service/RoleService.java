package com.senla.pdp.api.service;

import com.senla.pdp.model.Role;

import java.util.List;

public interface RoleService extends GenericService<Role, Integer> {

    List<Role> getAll();

}
