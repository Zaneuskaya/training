package com.senla.pdp.dto;

import com.senla.pdp.model.Position;
import com.senla.pdp.model.Skill;
import com.senla.pdp.model.SkillPosition;
import java.util.List;
import java.util.stream.Collectors;

public class PositionDto extends BasicDto{

    private Integer id;
    private String code;
    private String title;
    private List<Integer> skills;
    private String positionLevel;

    public PositionDto(){}

    public PositionDto(Position position){
        this.id = position.getId();
        this.code = position.getCode();
        this.title = position.getTitle();
        this.skills = position.getSkills().stream().map(SkillPosition::getSkill).map(Skill::getId).collect(Collectors.toList());
        this.positionLevel = position.getPositionLevel();
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

    public List<Integer> getSkills() {
	return skills;
    }

    public void setSkills(List<Integer> skills) {
	this.skills = skills;
    }

    public String getPositionLevel() {
	return positionLevel;
    }

    public void setPositionLevel(String positionLevel) {
	this.positionLevel = positionLevel;
    }
}
