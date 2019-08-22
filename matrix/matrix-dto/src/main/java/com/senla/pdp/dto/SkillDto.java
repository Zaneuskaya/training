package com.senla.pdp.dto;

import com.senla.pdp.model.Skill;

public class SkillDto extends BasicDto {

    private Integer id;
    private String title;
    private Integer skillGroup;

    public SkillDto(){}

    public SkillDto(Skill skill) {
	this.id = skill.getId();
	this.title = skill.getTitle();
	this.skillGroup = skill.getSkillGroup().getId();
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
