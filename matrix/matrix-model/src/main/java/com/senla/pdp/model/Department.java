package com.senla.pdp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "department", schema="public")
@Data
@EqualsAndHashCode(callSuper=false)
public class Department extends BasicEntity {

    @Id
    @GeneratedValue(generator = "increment")
    @Column(name = "id")
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "office")
    private Office office;

    @Column(name = "title")
    private String title;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private List<Employee> employees;

    @OneToOne
    @JoinColumn(name = "department_head")
    private Employee departmentHead;

    @ManyToOne
    @JoinColumn(name = "senior_department")
    private Department seniorDepartment;

}
