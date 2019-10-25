package com.microservice.authentication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity(name = "UserRoles")
@Table(name = "user_roles")
public class UserRoles {
    @Id
    @Column(name="user_role_id")
    private long userRoleId;
    @Column(name="user_role")
    private String userRole;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "username", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Users username;

    public UserRoles(long userRoleId, String userRole, Users username) {
        this.userRoleId = userRoleId;
        this.userRole = userRole;
        this.username = username;
    }

    public UserRoles() {
    }

    public long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Users getUsername() {        return username;
    }

    public void setUsername(Users username) {
        this.username = username;
    }
}