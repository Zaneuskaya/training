package com.senla.pdp.api.repository;

import com.senla.pdp.model.Office;

public interface OfficeRepository extends GenericRepository<Office,Integer> {

    Office getOfficeByCountryCityAddress(String country, String city, String address);

}
