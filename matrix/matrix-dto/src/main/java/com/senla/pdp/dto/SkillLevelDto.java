package com.senla.pdp.dto;

import com.senla.pdp.model.SkillLevel;

public class SkillLevelDto extends BasicDto{

    private Integer id;
    private String level;

    public SkillLevelDto(){}

    public SkillLevelDto(SkillLevel skillLevel){
        this.id = skillLevel.getId();
        this.level = skillLevel.getLevel();
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getLevel() {
	return level;
    }

    public void setLevel(String level) {
	this.level = level;
    }
}
