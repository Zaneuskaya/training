package com.senla.pdp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "skill_employee", schema="public")
@Data
@EqualsAndHashCode(callSuper = false)
public class SkillEmployee extends BasicEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "skill")
    private Skill skill;

    @Id
    @ManyToOne
    @JoinColumn(name = "employee")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "skill_level")
    private SkillLevel skillLevel;

}
