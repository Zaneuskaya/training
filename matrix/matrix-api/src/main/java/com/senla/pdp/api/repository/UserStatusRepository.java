package com.senla.pdp.api.repository;

import com.senla.pdp.api.exception.EntityNotFoundException;
import com.senla.pdp.model.UserStatus;


public interface UserStatusRepository extends GenericRepository<UserStatus, Integer> {

    UserStatus getUserStatusByTitle(String title);

}
