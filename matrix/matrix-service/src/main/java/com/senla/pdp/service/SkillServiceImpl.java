package com.senla.pdp.service;

import com.senla.pdp.api.exception.CreateEntityException;
import com.senla.pdp.api.exception.DeleteEntityException;
import com.senla.pdp.api.exception.EntityNotFoundException;
import com.senla.pdp.api.exception.UpdateEntityException;
import com.senla.pdp.api.repository.EmployeeRepository;
import com.senla.pdp.api.repository.KnowledgeFieldRepository;
import com.senla.pdp.api.repository.PositionRepository;
import com.senla.pdp.api.repository.SkillGroupRepository;
import com.senla.pdp.api.repository.SkillRepository;
import com.senla.pdp.api.service.SkillService;
import com.senla.pdp.model.Employee;
import com.senla.pdp.model.KnowledgeField;
import com.senla.pdp.model.Position;
import com.senla.pdp.model.Skill;
import com.senla.pdp.model.SkillEmployee;
import com.senla.pdp.model.SkillGroup;
import com.senla.pdp.model.SkillPosition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SkillServiceImpl implements SkillService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SkillServiceImpl.class);

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private SkillGroupRepository skillGroupRepository;

    @Autowired
    private KnowledgeFieldRepository knowledgeFieldRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public List<Skill> getSkillsByGroup(Integer skillGroupId) throws EntityNotFoundException {
        LOGGER.debug("Get skills of group with id {}", skillGroupId);
        List<Skill> skills;
        try {
            SkillGroup skillGroup = skillGroupRepository.get(skillGroupId);
            skills = skillGroup.getSkills();

        }
        catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
        return skills;
    }

    @Override
    public List<Skill> getSkillsByKnowledgeField(Integer knowledgeFieldId) throws EntityNotFoundException {
        LOGGER.debug("Get skills by knowledge field by id {}", knowledgeFieldId);
        List<Skill> skills = new ArrayList<>();
        try {
            KnowledgeField knowledgeField = knowledgeFieldRepository.get(knowledgeFieldId);
            List<SkillGroup> skillGroups = knowledgeField.getGroups();
            skillGroups.forEach(skillGroup -> skills.addAll(skillGroup.getSkills()));
        }
        catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
        return skills;
    }

    @Override
    public List<Skill> getEmployeeSkills(Integer employeeId) throws EntityNotFoundException {
        LOGGER.debug("Get employee skills by id {}", employeeId);
        List<Skill> skills;
        try {
            Employee employee = employeeRepository.get(employeeId);
            skills = employee.getSkills().stream().map(SkillEmployee::getSkill).collect(Collectors.toList());
        }
        catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
        return skills;
    }

    @Override
    public List<Skill> getPositionSkills(Integer positionId) throws EntityNotFoundException {
        LOGGER.debug("Get position {} skills", positionId);
        List<Skill> skills;
        try {
            Position position = positionRepository.get(positionId);
            skills = position.getSkills().stream().map(SkillPosition::getSkill).collect(Collectors.toList());
        }
        catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
        return skills;
    }

    @Override
    public Skill create(Skill entity) throws CreateEntityException {
        LOGGER.debug("Create skill {} ", entity.getTitle());
        try {
            skillRepository.add(entity);
        }
        catch (Exception e) {
            throw new CreateEntityException(e.getMessage());
        }
        return entity;
    }

    @Override
    public Skill getById(Integer id) throws EntityNotFoundException {
        LOGGER.debug("Get skill by id {}", id);
        Skill skill = skillRepository.get(id);
       if (skill == null) {
            throw new EntityNotFoundException("Skill is missing");
        }
        return skill;
    }

    @Override
    public Skill update(Skill skill) throws UpdateEntityException {
        LOGGER.debug("Update skill {}", skill.getTitle());
        try {
            skillRepository.update(skill);
        }
        catch (Exception e) {
            throw new UpdateEntityException(e.getMessage());
        }
        return skill;
    }

    @Override
    public void delete(Integer id) throws DeleteEntityException {
        LOGGER.debug("Delete skill by id {}", id);
        try {
            Skill skill = skillRepository.get(id);
            skillRepository.delete(skill);
        }
        catch (Exception e) {
            throw new DeleteEntityException(e.getMessage());
        }

    }
}
