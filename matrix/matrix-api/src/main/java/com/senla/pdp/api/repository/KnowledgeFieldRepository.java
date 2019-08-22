package com.senla.pdp.api.repository;

import com.senla.pdp.api.exception.EntityNotFoundException;
import com.senla.pdp.model.KnowledgeField;
import javax.persistence.criteria.CriteriaBuilder;

public interface KnowledgeFieldRepository extends GenericRepository<KnowledgeField, Integer> {

    KnowledgeField getKnowledgeFieldByTitle (String title);

}
