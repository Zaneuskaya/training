package com.senla.pdp.api.service;

import com.senla.pdp.api.exception.EntityNotFoundException;
import com.senla.pdp.api.exception.PositionExistsException;
import com.senla.pdp.api.exception.SkillExistsException;
import com.senla.pdp.model.Department;
import com.senla.pdp.model.Position;
import com.senla.pdp.model.Skill;
import com.senla.pdp.model.SkillLevel;
import java.util.List;

public interface PositionService extends GenericService<Position, Integer> {

    List<Position> getDepartmentEmployeesPositionsByDepartmentId(Integer id) throws EntityNotFoundException;

    void addSkillToPosition(Integer positionId, Integer skillId, SkillLevel skillLevel)
            throws SkillExistsException, EntityNotFoundException;

    void removeSkillFromPosition(Integer positionId, Integer skillId) throws EntityNotFoundException;

}
