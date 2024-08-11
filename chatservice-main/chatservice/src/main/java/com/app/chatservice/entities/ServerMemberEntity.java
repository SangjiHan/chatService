package com.app.chatservice.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "SERVER_MEMBER")
public class ServerMemberEntity {

	@EmbeddedId
	private ServerMemberId memberId;

	@Column(name = "SERVER_MEMBER_REG_DATE", nullable = false)
    private Date serverMemberRegDate;

	@Column(name = "SERVER_MEMBER_USER_NAME")
    private String serverMemberUserName;

	@Column(name = "SERVER_MEMBER_PFP")
	private String serverMemberPfp;

	@Column(name = "SERVER_MEMBER_LAST_VISITED")
	private String serverMemberLastVisited;

    
	public ServerMemberId getMemberId() {
		return memberId;
	}
	public void setMemberId(ServerMemberId memberId) {
		this.memberId = memberId;
    }
	public Date getServerMemberRegDate() {
		return serverMemberRegDate;
	}
	public void setServerMemberRegDate(Date serverMemberRegDate) {
		this.serverMemberRegDate = serverMemberRegDate;
	}
	public String getServerMemberUserName() {
		return serverMemberUserName;
	}
	public void setServerMemberUserName(String serverMemberUserName) {
		this.serverMemberUserName = serverMemberUserName;
    }
	public String getServerMemberPfp() {
		return serverMemberPfp;
	}
	public void setServerMemberPfp(String serverMemberPfp) {
		this.serverMemberPfp = serverMemberPfp;
	}

	public String getServerMemberLastVisited() {
		return serverMemberLastVisited;
	}
	public void setServerMemberLastVisited(String serverMemberLastVisited) {
		this.serverMemberLastVisited = serverMemberLastVisited;
	}

	@Override
	public String toString() {
		return "ServerMemberEntity [memberId=" + memberId + ", serverMemberRegDate=" + serverMemberRegDate
				+ ", serverMemberUserName=" + serverMemberUserName + ", serverMemberPfp=" + serverMemberPfp + "]";
	}


}
