package com.senla.pdp.service;

import com.senla.pdp.api.exception.CreateEntityException;
import com.senla.pdp.api.exception.DeleteEntityException;
import com.senla.pdp.api.exception.EntityNotFoundException;
import com.senla.pdp.api.exception.UpdateEntityException;
import com.senla.pdp.api.repository.PhotoRepository;
import com.senla.pdp.api.service.PhotoService;
import com.senla.pdp.model.Photo;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PhotoServiceImpl implements PhotoService {


    private static final Logger LOGGER = LoggerFactory.getLogger(PhotoServiceImpl.class);

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public Photo create(Photo entity) throws CreateEntityException {
        LOGGER.debug("Create photo {}", entity.getLabel());
        try {
            photoRepository.add(entity);
        }
        catch (Exception e) {
            throw new CreateEntityException(e.getMessage());
        }
        return entity;
    }

    @Override
    public Photo getById(Integer id) throws EntityNotFoundException {
        LOGGER.debug("Get photo by id {}", id);
        Photo photo = photoRepository.get(id);
        if (photo == null){
            throw new EntityNotFoundException("Photo is absent");
        }
        return photo;
    }

    @Override
    public Photo update(Photo photo) throws UpdateEntityException {
        LOGGER.debug("Update photo {}", photo.getLabel());
        try {
            photoRepository.update(photo);
        }
        catch (Exception e) {
            throw new UpdateEntityException(e.getMessage());
        }
        return photo;
    }

    @Override
    public void delete(Integer id) throws DeleteEntityException {
        LOGGER.debug("Delete photo by id {}", id);
        try {
            Photo photo = photoRepository.get(id);
            photoRepository.delete(photo);
        }
        catch (Exception e){
            throw new DeleteEntityException(e.getMessage());
        }
    }
}
