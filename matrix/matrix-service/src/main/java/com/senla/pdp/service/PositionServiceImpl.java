package com.senla.pdp.service;

import com.senla.pdp.api.exception.CreateEntityException;
import com.senla.pdp.api.exception.DeleteEntityException;
import com.senla.pdp.api.exception.EntityNotFoundException;
import com.senla.pdp.api.exception.SkillExistsException;
import com.senla.pdp.api.exception.UpdateEntityException;
import com.senla.pdp.api.repository.DepartmentRepository;
import com.senla.pdp.api.repository.PositionRepository;
import com.senla.pdp.api.repository.SkillRepository;
import com.senla.pdp.api.service.PositionService;
import com.senla.pdp.model.Department;
import com.senla.pdp.model.Employee;
import com.senla.pdp.model.Position;
import com.senla.pdp.model.Skill;
import com.senla.pdp.model.SkillLevel;
import com.senla.pdp.model.SkillPosition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PositionServiceImpl implements PositionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PositionServiceImpl.class);

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public List<Position> getDepartmentEmployeesPositionsByDepartmentId(Integer id) throws EntityNotFoundException {
        LOGGER.debug("Get positions of the department {}", id);
        List<Position> departmentPositions;
        try {
            Department department = departmentRepository.get(id);
            departmentPositions = department.getEmployees().stream().map(Employee::getPosition).collect(
                    Collectors.toList());
        }
        catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
        return departmentPositions;
    }

    @Override
    public void addSkillToPosition(Integer positionId, Integer skillId, SkillLevel skillLevel)
            throws EntityNotFoundException, SkillExistsException {
        LOGGER.debug("Add skill {} to position {} with level {}", skillId, positionId, skillLevel.getLevel());

        Position position = positionRepository.get(positionId);
        Skill skill = skillRepository.get(skillId);
        if (position == null || skill == null) {
            throw new EntityNotFoundException("Position or skill is not found");
        }
        List<SkillPosition> skillPositionList = position.getSkills();
        if (skillPositionList.stream().anyMatch(skillPosition -> skillPosition.getSkill().equals(skill))) {
            throw new SkillExistsException("Skill at this position exists");
        }
        else {
            SkillPosition skillPosition = new SkillPosition();
            skillPosition.setSkillLevel(skillLevel);
            skillPosition.setSkill(skill);
            skillPositionList.add(skillPosition);
            positionRepository.update(position);
            LOGGER.debug("Skill {} added at the position {} with level {}", skill.getTitle(), position.getTitle(),
                    skillLevel.getLevel());
        }


    }

    @Override
    public void removeSkillFromPosition(Integer positionId, Integer skillId) throws EntityNotFoundException {
        LOGGER.debug("Remove skill {} from the position {}", skillId, positionId);
        Position position = positionRepository.get(positionId);
        Skill skill = skillRepository.get(skillId);
        if (position == null || skill == null) {
            throw new EntityNotFoundException("Position or skill is not found");
        }
        position.getSkills().stream().dropWhile(skillPosition ->
                skillPosition.getSkill().equals(skill)
        );
        positionRepository.update(position);

    }

    @Override
    public Position create(Position entity) throws CreateEntityException {
        LOGGER.debug("Create position {} - {}", entity.getCode(), entity.getTitle());
        try {
            positionRepository.add(entity);
        }
        catch (Exception e) {
            throw new CreateEntityException(e.getMessage());
        }
        return entity;
    }

    @Override
    public Position getById(Integer id) throws EntityNotFoundException {
        LOGGER.debug("Get position by id {}", id);
        Position position = positionRepository.get(id);
        if(position == null){
            throw new EntityNotFoundException("Position is missing");
        }
        return position;
    }

    @Override
    public Position update(Position position) throws UpdateEntityException {
        LOGGER.debug("Position {} update", position.getTitle());
        try {
            positionRepository.update(position);
        }
        catch (Exception e) {
            throw new UpdateEntityException(e.getMessage());
        }
        return position;
    }

    @Override
    public void delete(Integer id) throws DeleteEntityException {
        LOGGER.debug("Delete position by id {}", id);
        try {
            Position position = positionRepository.get(id);
            positionRepository.delete(position);
        }
        catch (Exception e) {
            throw new DeleteEntityException(e.getMessage());
        }

    }
}
