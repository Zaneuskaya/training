package com.senla.pdp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "skill", schema="public")
@Data
@EqualsAndHashCode(callSuper = false)
public class Skill extends BasicEntity {

    @Id
    @GeneratedValue(generator = "increment")
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "skill_group")
    private SkillGroup skillGroup;

}
