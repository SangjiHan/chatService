package com.app.chatservice.entities;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "SERVER")
public class ServerEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "SERVER_ID")
    private String serverId;

	@Column(name = "SERVER_NAME")
    private String serverName;

	@Column(name = "SERVER_OWNER")
    private String serverOwner;

	@Temporal(TemporalType.DATE)
	@Column(name = "SERVER_REG_DATE", nullable = false)
    private Date serverRegDate;

	@Column(name = "SERVER_PFP")
    private String serverPfp;

	@Column(name = "SERVER_DESC")
	private String serverDesc;

	@PrePersist
	public void onCreate(){
		this.serverRegDate = new Date();
	}

	@Override
	public String toString() {
		return "ServerEntity [serverId=" + serverId + ", serverName=" + serverName + ", serverOwner=" + serverOwner
				+ ", serverRegDate=" + serverRegDate + ", serverPfp=" + serverPfp + ", serverDesc=" + serverDesc + "]";
	}
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getServerOwner() {
		return serverOwner;
	}
	public void setServerOwner(String serverOwner) {
		this.serverOwner = serverOwner;
	}
	public Date getServerRegDate() {
		return serverRegDate;
	}
	public void setServerRegDate(Date serverRegDate) {
		this.serverRegDate = serverRegDate;
	}
	public String getServerPfp() {
		return serverPfp;
	}
	public void setServerPfp(String serverPfp) {
		this.serverPfp = serverPfp;
	}
	public String getServerDesc() {
		return serverDesc;
	}
	public void setServerDesc(String serverDesc) {
		this.serverDesc = serverDesc;
	}
}
