package com.app.chatservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "USER_ROLE", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"USER_ROLE_NAME", "USER_ROLE_SERVER"})
})
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "USER_ROLE_ID")
    private String userRoleId;

	@Column(name = "USER_ROLE_NAME")
    private String userRoleName;

	@Column(name = "USER_ROLE_SERVER")
    private String userRoleServer;

    public String getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}
	public String getUserRoleName() {
		return userRoleName;
	}
	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}
	public String getUserRoleServer() {
		return userRoleServer;
	}
	public void setUserRoleServer(String userRoleServer) {
		this.userRoleServer = userRoleServer;
	}
}
