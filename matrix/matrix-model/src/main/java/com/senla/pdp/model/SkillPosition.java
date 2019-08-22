package com.senla.pdp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "skill_position", schema="public")
@Data
@EqualsAndHashCode(callSuper = false)
public class SkillPosition extends BasicEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "skill")
    private Skill skill;

    @Id
    @ManyToOne
    @JoinColumn(name = "position")
    private Position position;

    @ManyToOne
    @JoinColumn(name = "skill_level")
    private SkillLevel skillLevel;

}
