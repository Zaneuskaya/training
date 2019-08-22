package com.senla.pdp.repository;

import com.senla.pdp.model.SkillLevel;
import org.springframework.stereotype.Repository;

@Repository
public class SkillLevelRepositoryImpl extends GenericRepositoryImpl<SkillLevel,Integer> implements com.senla.pdp.api.repository.SkillLevelRepository {

    protected SkillLevelRepositoryImpl() {
        super(SkillLevel.class);
    }
}
