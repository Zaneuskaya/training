package com.senla.pdp.api.service;

import com.senla.pdp.api.exception.ChangePasswordException;
import com.senla.pdp.api.exception.EntityNotFoundException;
import com.senla.pdp.api.exception.LoginFailedException;
import com.senla.pdp.api.exception.UpdateEntityException;
import com.senla.pdp.model.Credentials;
import com.senla.pdp.model.UserStatus;

import javax.mail.MessagingException;
import java.util.List;

public interface CredentialsService extends GenericService<Credentials, Integer> {

    void sendActivation(String email) throws MessagingException;
    Credentials activateCredentials (String email) throws EntityNotFoundException, UpdateEntityException;
    Credentials login(String email, String password) throws EntityNotFoundException, LoginFailedException;
    Credentials updatePassword(String email, String oldPassword, String newPassword) throws EntityNotFoundException, LoginFailedException, ChangePasswordException;
    Credentials resetPassword(String email) throws EntityNotFoundException, ChangePasswordException, UpdateEntityException;
    List<Credentials> getCredentials();


}
