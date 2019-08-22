package com.senla.pdp.repository;

import com.senla.pdp.model.SkillGroup;
import org.springframework.stereotype.Repository;

@Repository
public class SkillGroupRepositoryImpl extends GenericRepositoryImpl<SkillGroup, Integer> implements com.senla.pdp.api.repository.SkillGroupRepository {

    protected SkillGroupRepositoryImpl() {
        super(SkillGroup.class);
    }
}
