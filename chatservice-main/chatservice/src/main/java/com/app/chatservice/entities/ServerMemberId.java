package com.app.chatservice.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ServerMemberId implements Serializable {

    @Column(name = "SERVER_MEMBER_USER")
    private String serverMemberUser;

    @Column(name = "SERVER_MEMBER_SERVER")
    private String serverMemberServer;

    // 생성자
    public ServerMemberId() {};
    public ServerMemberId(String serverMemberUser, String serverMemberServer) {
        this.serverMemberUser = serverMemberUser;
        this.serverMemberServer = serverMemberServer;
    }

    public String getServerMemberUser() {
        return serverMemberUser;
    }
    public void setServerMemberUser(String serverMemberUser) {
        this.serverMemberUser = serverMemberUser;
    }
    public String getServerMemberServer() {
        return serverMemberServer;
    }
    public void setServerMemberServer(String serverMemberServer) {
        this.serverMemberServer = serverMemberServer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerMemberId serverMemberId = (ServerMemberId) o;
        return Objects.equals(serverMemberUser, serverMemberId.serverMemberUser) && 
                Objects.equals(serverMemberServer, serverMemberId.serverMemberServer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serverMemberUser, serverMemberServer);
    }
    @Override
    public String toString() {
        return "ServerMemberId [serverMemberUser=" + serverMemberUser + ", serverMemberServer=" + serverMemberServer
                + "]";
    }


}
