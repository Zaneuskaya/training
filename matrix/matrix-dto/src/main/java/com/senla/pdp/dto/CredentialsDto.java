package com.senla.pdp.dto;

import com.senla.pdp.model.Credentials;
import com.senla.pdp.model.Role;
import com.senla.pdp.model.UserStatus;

public class CredentialsDto extends BasicDto {

    private Integer id;
    private String mail;
    private String password;
    private Integer role;
    private Integer userStatus;

    public CredentialsDto() {}

    public CredentialsDto(Credentials credentials) {
        this.id = credentials.getId();
        this.mail = credentials.getMail();
        this.password = credentials.getPassword();
        this.role = credentials.getRole().getId();
        this.userStatus = credentials.getUserStatus().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Credentials convertDtoToEntity() {
        Credentials credentials = new Credentials();
        credentials.setId(getId());
        credentials.setMail(getMail());
        credentials.setPassword(getPassword());
        return credentials;
    }
}
