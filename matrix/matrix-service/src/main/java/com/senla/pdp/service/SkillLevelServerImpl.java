package com.senla.pdp.service;

import com.senla.pdp.api.exception.CreateEntityException;
import com.senla.pdp.api.exception.DeleteEntityException;
import com.senla.pdp.api.exception.EntityNotFoundException;
import com.senla.pdp.api.exception.UpdateEntityException;
import com.senla.pdp.api.repository.SkillLevelRepository;
import com.senla.pdp.api.service.SkillLevelService;
import com.senla.pdp.model.SkillLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SkillLevelServerImpl implements SkillLevelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SkillLevelServerImpl.class);

    @Autowired
    private SkillLevelRepository skillLevelRepository;

    @Override
    public SkillLevel create(SkillLevel entity) throws CreateEntityException {
        LOGGER.debug("Skill level {} create", entity.getLevel());
        try {
            skillLevelRepository.add(entity);
        }
        catch (Exception e) {
            throw new CreateEntityException(e.getMessage());
        }
        return entity;
    }

    @Override
    public SkillLevel getById(Integer id) throws EntityNotFoundException {
        LOGGER.debug("Get skill level by id {}", id);
        SkillLevel skillLevel = skillLevelRepository.get(id);
        if (skillLevel == null) {
            throw new EntityNotFoundException("Skill level is missing");
        }
        return skillLevel;
    }

    @Override
    public SkillLevel update(SkillLevel skillLevel) throws UpdateEntityException {
        LOGGER.debug("Update skill level {}", skillLevel.getLevel());
        try {
            skillLevelRepository.update(skillLevel);
        }
        catch (Exception e) {
            throw new UpdateEntityException(e.getMessage());
        }
        return skillLevel;
    }

    @Override
    public void delete(Integer id) throws DeleteEntityException {
        LOGGER.debug("Delete skill level with id {}", id);
        try {
            SkillLevel skillLevel = skillLevelRepository.get(id);
            skillLevelRepository.delete(skillLevel);
        }
        catch (Exception e) {
            throw new DeleteEntityException(e.getMessage());
        }
    }
}
