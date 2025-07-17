package com.streamix.user.model;

import com.streamix.common.model.BaseEntity;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

import static com.streamix.user.constant.DbConstant.*;

@Entity
@Table(name = ROLES)
public class Role extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    @ManyToMany(mappedBy = ROLES)
    private Collection<User> users;

    @ManyToMany
    @JoinTable(name = ROLES_PRIVILEGES,
            joinColumns = @JoinColumn(name = ROLE_ID, referencedColumnName = ID),
            inverseJoinColumns = @JoinColumn(name = PRIVILEGE_ID, referencedColumnName = ID))
    private List<Privilege> privileges;

    public Role(String name, Collection<User> users, List<Privilege> privileges) {
        this.name = name;
        this.users = users;
        this.privileges = privileges;
    }

    public Role() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public Collection<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }
}