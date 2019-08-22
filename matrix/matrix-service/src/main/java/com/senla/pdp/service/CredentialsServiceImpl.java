package com.senla.pdp.service;

import com.senla.pdp.api.exception.ChangePasswordException;
import com.senla.pdp.api.exception.CreateEntityException;
import com.senla.pdp.api.exception.DeleteEntityException;
import com.senla.pdp.api.exception.EntityExistsException;
import com.senla.pdp.api.exception.EntityNotFoundException;
import com.senla.pdp.api.exception.LoginFailedException;
import com.senla.pdp.api.exception.UpdateEntityException;
import com.senla.pdp.api.repository.CredentialsRepository;
import com.senla.pdp.api.repository.UserStatusRepository;
import com.senla.pdp.api.service.CredentialsService;
import com.senla.pdp.model.Credentials;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CredentialsServiceImpl implements CredentialsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CredentialsServiceImpl.class);
    public static final String ACTIVE = "active";

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Autowired
    private UserStatusRepository userStatusRepository;

    @Autowired
    public JavaMailSender javaMailSender;


    @Override
    public void sendActivation(String email) throws MessagingException {
        LOGGER.debug("Sending activation link to {}", email);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(email);
        helper.setText(
                "Your account has been created. Please, follow the activation link  http://localhost:8080/credentials/activate/?mail=" + email);
        helper.setSubject("Senla matrix portal account activation");
        javaMailSender.send(message);
        LOGGER.debug("Activation message to {} has been sent", email);
    }

    @Override
    public Credentials activateCredentials(String email) throws EntityNotFoundException, UpdateEntityException {
        LOGGER.debug("Finding credentials for {}", email);
        Credentials credentials;
        try {
            credentials = credentialsRepository.findCredentialsByEmail(email);
        }
        catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
        try {
            credentials.setUserStatus(userStatusRepository.getUserStatusByTitle(ACTIVE));
            credentialsRepository.update(credentials);
        }
        catch (Exception e) {
            throw new UpdateEntityException(e.getMessage());
        }
        return credentials;
    }

    @Override
    public Credentials login(String email, String password) throws EntityNotFoundException, LoginFailedException {
        LOGGER.debug("Starting login of the user {}", email);
        Credentials credentials;
        try {
            credentials = credentialsRepository.findCredentialsByEmail(email);
        }
        catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
        if (credentials.getPassword().equals(password)) {
            if (credentials.getUserStatus().getTitle().equals(ACTIVE)) {
                LOGGER.debug("Login success for {}", email);
                return credentials;
            }
            else {
                throw new LoginFailedException("User is not active");
            }
        }
        else {
            throw new LoginFailedException("Login and password don't match");
        }
    }

    @Override
    public Credentials updatePassword(String email, String oldPassword, String newPassword)
            throws EntityNotFoundException, LoginFailedException, ChangePasswordException {
        LOGGER.debug("Starting update of the password for {}", email);
        Credentials credentials;
        try {
            credentials = credentialsRepository.findCredentialsByEmail(email);
        }
        catch (Exception e) {
            throw new EntityNotFoundException("Credentials are missing");
        }
        if (!credentials.getPassword().equals(oldPassword)) {
            throw new LoginFailedException("Login and old password don't match");
        }
        try {
            credentials.setPassword(newPassword);
            credentialsRepository.update(credentials);
        }
        catch (Exception e) {
            throw new ChangePasswordException(e.getMessage());
        }
        return credentials;
    }

    @Override
    public Credentials resetPassword(String email) throws EntityNotFoundException, ChangePasswordException {
        LOGGER.debug("Start reset of the password for {}", email);
        Credentials credentials;
        try {
            credentials = credentialsRepository.findCredentialsByEmail(email);
        }
        catch (Exception e) {
            throw new EntityNotFoundException("Credentials are missing");
        }
        String password = RandomStringUtils.random(10, true, true);
        try {
            credentials.setPassword(password);
            credentialsRepository.update(credentials);
        }
        catch (Exception e) {
            throw new ChangePasswordException(e.getMessage());
        }
        return credentials;
    }

    @Override
    public List<Credentials> getCredentials() {
        LOGGER.debug("Getting all credentials of the system");
        return credentialsRepository.getAll();
    }

    @Override
    public Credentials create(Credentials entity) throws CreateEntityException {
        LOGGER.debug("Add user {}", entity.getMail());
        try {
            if (credentialsRepository.findCredentialsByEmail(entity.getMail()) != null) {
                throw new EntityExistsException("Credentials with this mail already exists");
            }
            credentialsRepository.add(entity);
        }
        catch (Exception e) {
            throw new CreateEntityException(e.getMessage());
        }
        return entity;
    }

    @Override
    public Credentials getById(Integer id) throws EntityNotFoundException {
        LOGGER.debug("Credentials search by id {}", id);
        Credentials credentials = credentialsRepository.get(id);
        if (credentials == null) {
            throw new EntityNotFoundException("Credentials not found");
        }


        return credentials;
    }

    @Override
    public Credentials update(Credentials credentials) throws UpdateEntityException {
        LOGGER.debug("Update credentials of {}", credentials.getMail());
        try {
            credentialsRepository.update(credentials);
        }
        catch (Exception e) {
            throw new UpdateEntityException(e.getMessage());
        }
        return credentials;
    }

    @Override
    public void delete(Integer t) throws DeleteEntityException {
        LOGGER.debug("Delete credentials with id {}", t);
        try {
            Credentials credentials = credentialsRepository.get(t);
            credentialsRepository.delete(credentials);
        }
        catch (Exception e) {
            throw new DeleteEntityException(e.getMessage());
        }
    }
}
