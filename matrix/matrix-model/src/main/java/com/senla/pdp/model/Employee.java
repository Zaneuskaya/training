package com.senla.pdp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employee", schema="public")
@Data
@EqualsAndHashCode(callSuper=false)
public class Employee extends BasicEntity{

    @Id
    @GeneratedValue(generator = "increment")
    @Column(name = "id")
    private Integer id;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "department")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "position")
    private Position position;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<SkillEmployee> skills;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credentials")
    private Credentials credentials;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo")
    private Photo photo;

}
