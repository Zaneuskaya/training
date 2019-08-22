package com.senla.pdp.service;

import com.senla.pdp.api.exception.CreateEntityException;
import com.senla.pdp.api.exception.DeleteEntityException;
import com.senla.pdp.api.exception.EntityExistsException;
import com.senla.pdp.api.exception.EntityNotFoundException;
import com.senla.pdp.api.exception.UpdateEntityException;
import com.senla.pdp.api.repository.KnowledgeFieldRepository;
import com.senla.pdp.api.service.KnowledgeFieldService;
import com.senla.pdp.model.KnowledgeField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class KnowledgeFieldServiceImpl implements KnowledgeFieldService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KnowledgeFieldServiceImpl.class);

    @Autowired
    private KnowledgeFieldRepository knowledgeFieldRepository;

    @Override
    public KnowledgeField create(KnowledgeField entity) throws CreateEntityException {
        LOGGER.debug("Create Knowledge field {}", entity.getTitle());
        try {
            if (knowledgeFieldRepository.getKnowledgeFieldByTitle(entity.getTitle()) != null) {
                throw new EntityExistsException("Knowledge field already exists");
            }
            knowledgeFieldRepository.add(entity);
        }
        catch (Exception e) {
            throw new CreateEntityException(e.getMessage());
        }
        return entity;
    }

    @Override
    public KnowledgeField getById(Integer id) throws EntityNotFoundException {
        LOGGER.debug("Get knowledge fiels by id {}", id);
        KnowledgeField knowledgeField = knowledgeFieldRepository.get(id);
       if (knowledgeField == null){
            throw new EntityNotFoundException("Knowledge field is not present");
        }
        return knowledgeField;
    }

    @Override
    public KnowledgeField update(KnowledgeField knowledgeField) throws UpdateEntityException {
        LOGGER.debug("Update knowledge field", knowledgeField.getTitle());
        try {
            knowledgeFieldRepository.update(knowledgeField);
        }
        catch (Exception e) {
            throw new UpdateEntityException(e.getMessage());
        }
        return knowledgeField;
    }

    @Override
    public void delete(Integer id) throws DeleteEntityException {
        LOGGER.debug("Delete knowledge field by id {}", id);
        try {
            KnowledgeField knowledgeField = knowledgeFieldRepository.get(id);
            knowledgeFieldRepository.delete(knowledgeField);
        }
        catch (Exception e) {
            throw new DeleteEntityException(e.getMessage());
        }
    }
}
