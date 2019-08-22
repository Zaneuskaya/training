package com.senla.pdp.dto;

import com.senla.pdp.model.Photo;

public class PhotoDto extends BasicDto{

    private Integer id;
    private byte[] image;
    private String label;

    public PhotoDto(){}

    public PhotoDto(Photo photo){
        this.id = photo.getId();
        this.label = photo.getLabel();
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public byte[] getImage() {
	return image;
    }

    public void setImage(byte[] image) {
	this.image = image;
    }

    public String getLabel() {
	return label;
    }

    public void setLabel(String label) {
	this.label = label;
    }
}
