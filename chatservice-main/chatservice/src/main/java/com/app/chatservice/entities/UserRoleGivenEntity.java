package com.app.chatservice.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER_ROLE_GIVEN")
public class UserRoleGivenEntity {
    
    @EmbeddedId
    private UserRoleGivenId userGivenRole;

    public UserRoleGivenId getUserGivenRole() {
        return userGivenRole;
    }
    public void setUserGivenRole(UserRoleGivenId userGivenRole) {
        this.userGivenRole = userGivenRole;
    }
}
