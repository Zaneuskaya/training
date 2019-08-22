package com.senla.pdp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "skill_group", schema="public")
@Data
@EqualsAndHashCode(callSuper = false)
public class SkillGroup extends BasicEntity {

    @Id
    @GeneratedValue(generator = "increment")
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "knowledge_field")
    private KnowledgeField knowledgeField;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skillGroup")
    private List<Skill> skills;

}
