package com.github.legionarks.model.user.role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.quarkus.security.jpa.RolesValue;

@Entity
@Table(name = "ROLE")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Short id;

    @RolesValue
    @Column(name = "TYPE", unique = true)
    private String type;

    public Role() {
    }

    public Role(RoleType type) {
        this.type = type.name();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
