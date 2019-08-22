package com.senla.pdp.api.service;

import com.senla.pdp.api.exception.EntityNotFoundException;
import com.senla.pdp.model.Employee;
import com.senla.pdp.model.KnowledgeField;
import com.senla.pdp.model.Position;
import com.senla.pdp.model.Skill;
import com.senla.pdp.model.SkillGroup;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface SkillService extends GenericService<Skill, Integer> {

    List<Skill> getSkillsByGroup(Integer skillGroupId) throws EntityNotFoundException;

    List<Skill> getSkillsByKnowledgeField(Integer knowledgeFieldId) throws EntityNotFoundException;

    List<Skill> getEmployeeSkills(Integer employeeId) throws EntityNotFoundException;

    List<Skill> getPositionSkills(Integer positionId) throws EntityNotFoundException;

}
