package com.senla.pdp.api.repository;

import com.senla.pdp.model.Role;
import java.util.List;

public interface RoleRepository extends GenericRepository<Role, Integer> {

    List<Role> getAll();

    List<Role> getRolesByTitle(String title);

}
