package com.app.chatservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER_DEFAULT_STATUS")
public class UserDefaultStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "USER_DEFAULT_STATUS_USER")
    private String userDefaultStatusUser;

	@Column(name = "USER_DEFAULT_STATUS_STATUS")
    private String userDefaultStatusStatus;

    public String getUserDefaultStatusUser() {
		return userDefaultStatusUser;
	}
	public void setUserDefaultStatusUser(String userDefaultStatusUser) {
		this.userDefaultStatusUser = userDefaultStatusUser;
	}
	public String getUserDefaultStatusStatus() {
		return userDefaultStatusStatus;
	}
	public void setUserDefaultStatusStatus(String userDefaultStatusStatus) {
		this.userDefaultStatusStatus = userDefaultStatusStatus;
	}
}
