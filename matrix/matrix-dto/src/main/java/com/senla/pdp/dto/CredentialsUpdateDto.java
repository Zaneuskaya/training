package com.senla.pdp.dto;

import com.senla.pdp.model.Credentials;

public class CredentialsUpdateDto extends BasicDto {

    private Integer id;
    private String mail;
    private String oldPassword;
    private String newPassword;

    public CredentialsUpdateDto(){}

    public CredentialsUpdateDto(Integer id, String mail, String oldPassword, String newPassword) {
        this.id = id;
        this.mail = mail;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
