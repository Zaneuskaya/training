package com.senla.pdp.controller;

import com.senla.pdp.aop.Audit;
import com.senla.pdp.api.exception.ChangePasswordException;
import com.senla.pdp.api.exception.CreateEntityException;
import com.senla.pdp.api.exception.DeleteEntityException;
import com.senla.pdp.api.exception.EntityExistsException;
import com.senla.pdp.api.exception.EntityNotFoundException;
import com.senla.pdp.api.exception.LoginFailedException;
import com.senla.pdp.api.exception.UpdateEntityException;
import com.senla.pdp.api.service.CredentialsService;
import com.senla.pdp.api.service.RoleService;
import com.senla.pdp.api.service.UserStatusService;
import com.senla.pdp.dto.CredentialsDto;
import com.senla.pdp.dto.CredentialsUpdateDto;
import com.senla.pdp.model.Credentials;
import com.senla.pdp.model.Role;
import com.senla.pdp.model.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("credentials")
public class CredentialsController {

    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private UserStatusService userStatusService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/{id}")
    public CredentialsDto getCredentials(
            @PathVariable
                    Integer id) throws EntityNotFoundException {
        return new CredentialsDto(credentialsService.getById(id));
    }

    @PostMapping("/sendActivation")
    public void sendActivation(
            @RequestParam
                    String mail) throws MessagingException {
        credentialsService.sendActivation(mail);
    }

    @PostMapping("/activate")
    public CredentialsDto activateCredentials(
            @RequestParam
                    String mail)
            throws UpdateEntityException, EntityNotFoundException {
        Credentials credentials = credentialsService.activateCredentials(mail);
        return new CredentialsDto(credentials);
    }

    @Audit
    @GetMapping("/list")
    public List<CredentialsDto> getAllCredentials() {
        List<Credentials> credentials = credentialsService.getCredentials();
        return credentials.stream().map(CredentialsDto::new).collect(Collectors.toList());
    }

    @PostMapping("/login")
    public CredentialsDto login(
            @RequestBody
                    CredentialsDto credentialsDto)
            throws LoginFailedException, EntityNotFoundException {
        Credentials credentials = credentialsService.login(credentialsDto.getMail(), credentialsDto.getPassword());
        return new CredentialsDto(credentials);
    }

    @PostMapping("/changePassword")
    public CredentialsDto changePassword(
            @RequestBody
                    CredentialsUpdateDto credentialsUpdateDto)
            throws EntityNotFoundException, ChangePasswordException, LoginFailedException {
        Credentials credentials = credentialsService.updatePassword(credentialsUpdateDto.getMail(),
                credentialsUpdateDto.getOldPassword(), credentialsUpdateDto.getNewPassword());
        return new CredentialsDto(credentials);
    }

    @PostMapping("/resetPassword")
    public CredentialsDto resetPassword(
            @RequestParam
                    String mail)
            throws EntityNotFoundException, ChangePasswordException, UpdateEntityException {
        Credentials credentials = credentialsService.resetPassword(mail);
        return new CredentialsDto(credentials);
    }

    @PostMapping("/create")
    public CredentialsDto create(
            @RequestBody
                    CredentialsDto credentialsDto)
            throws EntityExistsException, CreateEntityException, EntityNotFoundException {
        Credentials credentials = credentialsDto.convertDtoToEntity();
        Role role = roleService.getById(credentialsDto.getRole());
        UserStatus userStatus = userStatusService.getById(credentialsDto.getUserStatus());
        credentials.setRole(role);
        credentials.setUserStatus(userStatus);
        credentialsService.create(credentials);
        return new CredentialsDto(credentials);
    }

    @PutMapping("/update")
    public CredentialsDto update(
            @RequestBody
                    CredentialsDto credentialsDto)
            throws EntityNotFoundException, UpdateEntityException {
        Credentials credentials = credentialsDto.convertDtoToEntity();
        credentials.setUserStatus(userStatusService.getById(credentialsDto.getUserStatus()));
        credentials.setRole(roleService.getById(credentialsDto.getRole()));
        credentialsService.update(credentials);
        return new CredentialsDto(credentials);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(
            @PathVariable
                    Integer id) throws DeleteEntityException {
        credentialsService.delete(id);
    }
}
