package com.senla.pdp.repository;

import com.senla.pdp.api.repository.PhotoRepository;
import com.senla.pdp.model.Photo;
import org.springframework.stereotype.Repository;

@Repository
public class PhotoRepositoryImpl extends GenericRepositoryImpl<Photo, Integer> implements PhotoRepository {

    protected PhotoRepositoryImpl() {
        super(Photo.class);
    }
}
