package com.senla.pdp.dto;

import com.senla.pdp.model.UserStatus;

public class UserStatusDto extends BasicDto{

    private Integer id;
    private String title;

    public UserStatusDto(){}

    public UserStatusDto(UserStatus status){
        this.id = status.getId();
        this.title = status.getTitle();

    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }
}
