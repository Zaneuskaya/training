package com.senla.pdp.service;

import com.senla.pdp.api.exception.CreateEntityException;
import com.senla.pdp.api.exception.DeleteEntityException;
import com.senla.pdp.api.exception.EntityExistsException;
import com.senla.pdp.api.exception.EntityNotFoundException;
import com.senla.pdp.api.exception.UpdateEntityException;
import com.senla.pdp.api.repository.OfficeRepository;
import com.senla.pdp.api.service.OfficeService;
import com.senla.pdp.model.Office;
import org.hibernate.sql.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OfficeServiceImpl implements OfficeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OfficeServiceImpl.class);

    @Autowired private OfficeRepository officeRepository;

    @Override
    public Office create(Office entity) throws CreateEntityException {
        LOGGER.debug("Create office {} at {}", entity.getAddress(), entity.getCity());
        try {
            if (officeRepository.getOfficeByCountryCityAddress(entity.getCountry(),entity.getCity(),
                    entity.getAddress()) != null){
                throw new EntityExistsException("Office already exists");
            }
            officeRepository.add(entity);
        }
        catch (Exception e) {
            throw new CreateEntityException(e.getMessage());
        }
        return entity;
    }

    @Override
    public Office getById(Integer id) throws EntityNotFoundException {
        LOGGER.debug("Get office by id {}", id);
        Office office = officeRepository.get(id);
        if (office == null){
            throw new EntityNotFoundException("Office is not present");
        }
        return office;
    }

    @Override
    public Office update(Office office) throws UpdateEntityException {
        LOGGER.debug("Update office {}, {} entity", office.getAddress(), office.getCity());
        Office updatedOffice;
        try {
            officeRepository.update(office);
            updatedOffice = officeRepository.getOfficeByCountryCityAddress(office.getCountry(), office.getCity(),
                    office.getAddress());
        }
        catch (Exception e) {
            throw new UpdateEntityException(e.getMessage());
        }
        return updatedOffice;
    }

    @Override
    public void delete(Integer id) throws DeleteEntityException {
        LOGGER.debug("Delete office {}", id);
        try {
            Office office = officeRepository.get(id);
            officeRepository.delete(office);
        }
        catch (Exception e) {
            throw new DeleteEntityException(e.getMessage());
        }

    }
}
