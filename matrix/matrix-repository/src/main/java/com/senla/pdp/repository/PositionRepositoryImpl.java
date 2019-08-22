package com.senla.pdp.repository;

import com.senla.pdp.api.repository.PositionRepository;
import com.senla.pdp.model.Position;
import org.springframework.stereotype.Repository;

@Repository
public class PositionRepositoryImpl extends GenericRepositoryImpl<Position, Integer> implements PositionRepository {

    protected PositionRepositoryImpl() {
        super(Position.class);
    }
}
