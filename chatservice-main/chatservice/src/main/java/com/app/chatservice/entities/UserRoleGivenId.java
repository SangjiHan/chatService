package com.app.chatservice.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UserRoleGivenId implements Serializable {

    @Column(name = "USER_ROLE_GIVEN_ROLE")
    private String userRoleGivenRole;

    @Column(name = "USER_ROLE_GIVEN_USER")
    private String userRoleGivenUser;

    public UserRoleGivenId() {};
    public UserRoleGivenId(String userRoleGivenRole, String userRoleGivenUser) {
        this.userRoleGivenRole = userRoleGivenRole;
        this.userRoleGivenUser = userRoleGivenUser;
    }

    public String getUserRoleGivenRole() {
        return userRoleGivenRole;
    }

    public void setUserRoleGivenRole(String userRoleGivenRole) {
        this.userRoleGivenRole = userRoleGivenRole;
    }

    public String getUserRoleGivenUser() {
        return userRoleGivenUser;
    }
    
    public void setUserRoleGivenUser(String userRoleGivenUser) {
        this.userRoleGivenUser = userRoleGivenUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleGivenId userRoleGivenId = (UserRoleGivenId) o;
        return Objects.equals(userRoleGivenRole, userRoleGivenId.userRoleGivenRole) && 
                Objects.equals(userRoleGivenUser, userRoleGivenId.userRoleGivenUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userRoleGivenRole, userRoleGivenUser);
    }
}
