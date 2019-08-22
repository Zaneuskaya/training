package com.senla.pdp.dto;

import com.senla.pdp.model.KnowledgeField;
import com.senla.pdp.model.SkillGroup;
import java.util.List;
import java.util.stream.Collectors;

public class KnowledgeFieldDto extends BasicDto {

    private Integer id;
    private String title;
    private List<Integer> skillGroups;

    public KnowledgeFieldDto(){}

    public KnowledgeFieldDto(KnowledgeField knowledgeField) {
	this.id = knowledgeField.getId();
	this.title = knowledgeField.getTitle();
	this.skillGroups = knowledgeField.getGroups().stream().map(SkillGroup::getId).collect(Collectors.toList());
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

    public List<Integer> getSkillGroups() {
	return skillGroups;
    }

    public void setSkillGroups(List<Integer> skillGroups) {
	this.skillGroups = skillGroups;
    }
}
