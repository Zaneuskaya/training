package com.senla.pdp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "photo", schema="public")
@Data
@EqualsAndHashCode(callSuper=false)
public class Photo extends BasicEntity {

    @Id
    @GeneratedValue(generator = "increment")
    @Column(name = "id")
    private Integer id;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "label")
    private String label;

}
