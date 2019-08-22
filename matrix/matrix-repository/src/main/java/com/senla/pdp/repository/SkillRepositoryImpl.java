package com.senla.pdp.repository;

import com.senla.pdp.api.repository.SkillRepository;
import com.senla.pdp.model.Skill;
import org.springframework.stereotype.Repository;

@Repository
public class SkillRepositoryImpl extends  GenericRepositoryImpl<Skill,Integer> implements SkillRepository {

    protected SkillRepositoryImpl() {
        super(Skill.class);
    }
}
