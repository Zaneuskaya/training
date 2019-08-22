package com.senla.pdp.dto;

import com.senla.pdp.model.Position;

public class PositionSimpleDto extends BasicDto {

    private Integer id;
    private String code;
    private String title;

    public PositionSimpleDto(){}

    public PositionSimpleDto(Position position){
        this.id = position.getId();
        this.code = position.getCode();
        this.title = position.getTitle();
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }
}
