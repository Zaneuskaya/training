package com.senla.pdp.service;

import com.senla.pdp.api.exception.CreateEntityException;
import com.senla.pdp.api.exception.DeleteEntityException;
import com.senla.pdp.api.exception.EntityNotFoundException;
import com.senla.pdp.api.exception.UpdateEntityException;
import com.senla.pdp.api.repository.UserStatusRepository;
import com.senla.pdp.api.service.UserStatusService;
import com.senla.pdp.model.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserStatusServiceImpl implements UserStatusService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserStatusServiceImpl.class);

    @Autowired
    private UserStatusRepository userStatusRepository;

    @Override
    public UserStatus create(UserStatus entity) throws CreateEntityException {
        LOGGER.debug("Create user status {}", entity.getTitle());
        try {
            userStatusRepository.add(entity);
        }
        catch (Exception e) {
            throw new CreateEntityException(e.getMessage());
        }
        return entity;
    }

    @Override
    public UserStatus getById(Integer id) throws EntityNotFoundException {
        LOGGER.debug("Get user status by id {}", id);
        UserStatus userStatus = userStatusRepository.get(id);
        if (userStatus == null) {
            throw new EntityNotFoundException("User status is missing");
        }
        return userStatus;
    }

    @Override
    public UserStatus update(UserStatus userStatus) throws UpdateEntityException {
        LOGGER.debug("Update user status {}", userStatus.getTitle());
        try {
            userStatusRepository.update(userStatus);
        }
        catch (Exception e) {
            throw new UpdateEntityException(e.getMessage());
        }
        return userStatus;
    }

    @Override
    public void delete(Integer id) throws DeleteEntityException {
        LOGGER.debug("Delete user status with id {}", id);
        try {
            UserStatus userStatus = userStatusRepository.get(id);
            userStatusRepository.delete(userStatus);
        }
        catch (Exception e) {
            throw new DeleteEntityException(e.getMessage());
        }
    }
}
