package com.senla.pdp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "credentials", schema="public")
@Data
@EqualsAndHashCode(callSuper = false)
public class Credentials extends BasicEntity {

    @Id
    @GeneratedValue(generator = "increment")
    @Column(name = "id")
    private Integer id;

    @Column(name = "mail")
    private String mail;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role", columnDefinition = "int default 0")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "user_status", columnDefinition = "int default 0")
    private UserStatus userStatus;

}
