package com.senla.pdp.dto;

import com.senla.pdp.model.Office;

public class OfficeDto extends BasicDto{

    private Integer id;
    private String country;
    private String city;
    private String address;

    public OfficeDto(){}

    public OfficeDto(Office office) {
        this.id = office.getId();
        this.country = office.getCountry();
        this.city = office.getCity();
        this.address = office.getAddress();
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getCountry() {
	return country;
    }

    public void setCountry(String country) {
	this.country = country;
    }

    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }
}
