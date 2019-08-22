package com.senla.pdp.service;

import com.senla.pdp.api.exception.CreateEntityException;
import com.senla.pdp.api.exception.DeleteEntityException;
import com.senla.pdp.api.exception.EntityNotFoundException;
import com.senla.pdp.api.exception.UpdateEntityException;
import com.senla.pdp.api.repository.SkillGroupRepository;
import com.senla.pdp.api.service.SkillGroupService;
import com.senla.pdp.model.SkillGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SkillGroupServiceImpl implements SkillGroupService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SkillGroupServiceImpl.class);

    @Autowired
    private SkillGroupRepository skillGroupRepository;

    @Override
    public SkillGroup create(SkillGroup entity) throws CreateEntityException {
        LOGGER.debug("Create skill group {}", entity.getTitle());
        try {
            skillGroupRepository.add(entity);
        }
        catch (Exception e) {
            throw new CreateEntityException(e.getMessage());
        }
        return entity;
    }

    @Override
    public SkillGroup getById(Integer id) throws EntityNotFoundException {
        LOGGER.debug("Get skill group by id {}", id);
        SkillGroup skillGroup = skillGroupRepository.get(id);
        if (skillGroup == null) {
            throw new EntityNotFoundException("Skill group is missing");
        }
        return skillGroup;
    }

    @Override
    public SkillGroup update(SkillGroup t) throws UpdateEntityException {
        LOGGER.debug("Skill group is updated {}", t.getTitle());
        try {
            skillGroupRepository.update(t);
        }
        catch (Exception e) {
            throw new UpdateEntityException(e.getMessage());
        }
        return t;
    }

    @Override
    public void delete(Integer id) throws DeleteEntityException {
        LOGGER.debug("Delete skill group by id {}", id);
        try {
            SkillGroup skillGroup = skillGroupRepository.get(id);
            skillGroupRepository.delete(skillGroup);
        }
        catch (Exception e) {
            throw new DeleteEntityException(e.getMessage());
        }
    }
}
