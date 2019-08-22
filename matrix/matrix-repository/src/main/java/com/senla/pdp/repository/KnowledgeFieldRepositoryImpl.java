package com.senla.pdp.repository;

import com.senla.pdp.api.repository.KnowledgeFieldRepository;
import com.senla.pdp.model.KnowledgeField;
import com.senla.pdp.model.KnowledgeField_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class KnowledgeFieldRepositoryImpl extends GenericRepositoryImpl<KnowledgeField, Integer>
        implements KnowledgeFieldRepository {

    protected KnowledgeFieldRepositoryImpl() {
        super(KnowledgeField.class);
    }

    @Override
    public KnowledgeField getKnowledgeFieldByTitle(String title) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<KnowledgeField> knowledgeFieldCriteriaQuery = criteriaBuilder.createQuery(KnowledgeField.class);
        Root<KnowledgeField> knowledgeFieldRoot = knowledgeFieldCriteriaQuery.from(KnowledgeField.class);
        knowledgeFieldCriteriaQuery.where(criteriaBuilder.equal(knowledgeFieldRoot.get(KnowledgeField_.TITLE),title));
        return getEntityManager().createQuery(knowledgeFieldCriteriaQuery).getSingleResult();
    }
}
