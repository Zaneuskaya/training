package com.senla.pdp.service;

import com.senla.pdp.api.exception.CreateEntityException;
import com.senla.pdp.api.exception.DeleteEntityException;
import com.senla.pdp.api.exception.EntityExistsException;
import com.senla.pdp.api.exception.EntityNotFoundException;
import com.senla.pdp.api.exception.UpdateEntityException;
import com.senla.pdp.api.repository.RoleRepository;
import com.senla.pdp.api.service.RoleService;
import com.senla.pdp.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role create(Role entity) throws CreateEntityException, EntityExistsException {
        LOGGER.debug("Creating a new Role {}", entity);
        if (!roleRepository.getRolesByTitle(entity.getRole()).isEmpty()) {
            throw new EntityExistsException("Role can't be duplicated");
        }
        try {
            roleRepository.add(entity);
        }
        catch (Exception e) {
            throw new CreateEntityException(e.getMessage());
        }
        return entity;
    }

    @Override
    public Role getById(Integer id) throws EntityNotFoundException {
        LOGGER.debug("Receiving Role by id {}", id);
        Role role = roleRepository.get(id);
            LOGGER.debug("Role {} received by id", role);
       if (role == null) {
            throw new EntityNotFoundException("Role is missing");
        }
        return role;
    }

    @Override
    public List<Role> getAll() {
        LOGGER.debug("Starting to get list of Roles");
        List<Role> roleList = roleRepository.getAll();
        LOGGER.debug("Roles list contains {} roles", roleList.size());
        return roleList;
    }

    @Override
    public Role update(Role entity) throws UpdateEntityException {
        LOGGER.debug("Starting to edit role {}", entity);
        try {
            roleRepository.update(entity);
        }
        catch (Exception e) {
            throw new UpdateEntityException(e.getMessage());
        }
        return entity;
    }

    @Override
    public void delete(Integer id) throws DeleteEntityException {
        LOGGER.debug("Starting to delete role by id ", id);
        try {
            Role role = roleRepository.get(id);
            roleRepository.delete(role);
        }
        catch (Exception e) {
            throw new DeleteEntityException(e.getMessage());
        }
    }

}
