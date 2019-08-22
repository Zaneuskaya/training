package com.senla.pdp.dto;

import com.senla.pdp.model.KnowledgeField;

public class KnowledgeFieldSimpleDto extends BasicDto {

    private Integer id;
    private String title;

    public KnowledgeFieldSimpleDto(){}

    public KnowledgeFieldSimpleDto(KnowledgeField knowledgeField) {
        this.id = knowledgeField.getId();
        this.title = knowledgeField.getTitle();
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
