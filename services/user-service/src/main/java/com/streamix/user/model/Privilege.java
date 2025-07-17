package com.streamix.user.model;

import com.streamix.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.List;

import static com.streamix.user.constant.DbConstant.PRIVILEGES;

@Entity
@Table(name = PRIVILEGES)
public class Privilege extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = PRIVILEGES)
    private List<Role> roles;

    public Privilege(String name, List<Role> roles) {
        this.name = name;
        this.roles = roles;
    }

    public Privilege(String name) {
        this.name = name;
    }

    public Privilege() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
