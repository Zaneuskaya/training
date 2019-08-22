package com.senla.pdp.dto;

import com.senla.pdp.model.Skill;
import com.senla.pdp.model.SkillGroup;
import java.util.List;
import java.util.stream.Collectors;

public class SkillGroupDto extends BasicDto{

    private Integer id;
    private String title;
    private KnowledgeFieldSimpleDto knowledgeField;
    private List<Integer> skills;

    public SkillGroupDto(){}

    public SkillGroupDto(SkillGroup skillGroup){
        this.id = skillGroup.getId();
        this.title = skillGroup.getTitle();
        this.knowledgeField = new KnowledgeFieldSimpleDto(skillGroup.getKnowledgeField());
        this.skills = skillGroup.getSkills().stream().map(Skill::getId).collect(Collectors.toList());
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

    public KnowledgeFieldSimpleDto getKnowledgeField() {
	return knowledgeField;
    }

    public void setKnowledgeField(KnowledgeFieldSimpleDto knowledgeField) {
	this.knowledgeField = knowledgeField;
    }

    public List<Integer> getSkills() {
	return skills;
    }

    public void setSkills(List<Integer> skills) {
	this.skills = skills;
    }
}
