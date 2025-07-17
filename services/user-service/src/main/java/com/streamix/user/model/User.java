package com.streamix.user.model;

import com.streamix.common.model.BaseEntity;
import jakarta.persistence.*;

import java.util.List;

import static com.streamix.user.constant.DbConstant.*;

@Entity
@Table(name = USERS)
public class User extends BaseEntity {
    private String email;
    private String password;
    private String name;
    private boolean enabled = true;
    private boolean tokenExpired = false;

    @ManyToMany
    @JoinTable(name = USER_ROLES,
            joinColumns = @JoinColumn(name = USER_ID, referencedColumnName = ID),
            inverseJoinColumns = @JoinColumn(name = ROLE_ID, referencedColumnName = ID))
    private List<Role> roles;

    public User(String email, String password, String name, List<Role> roles) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.roles = roles;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isTokenExpired() {
        return tokenExpired;
    }

    public void setTokenExpired(boolean tokenExpired) {
        this.tokenExpired = tokenExpired;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
