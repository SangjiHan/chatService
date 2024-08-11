package com.app.chatservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Date;
// import org.springframework.data.jpa.repository.Query;

import com.app.chatservice.entities.ServerEntity;

public interface ServerRepository extends JpaRepository<ServerEntity, String> {
    
    // ServerId로 서버 찾기
    ServerEntity findByServerId(String serverId);
    // ServerName으로 서버 찾기
    ServerEntity findByServerName(String serverName);
    // ServerOwner로 서버 모두 찾기
    List<ServerEntity> findByServerOwner(String serverOwner);
    // ServerRegDate로 서버 모두 찾기
    List<ServerEntity> findByServerRegDate(Date serverRegDate);
    // ServerPfp로 서버 모두 찾기
    List<ServerEntity> findByServerPfp(String serverPfp);
    // ServerDesc로 서버 모두 찾기
    List<ServerEntity> findByServerDesc(String serverDesc);

    // 서버아이디와 서버오너로 찾기
    @Query(value = "SELECT * FROM SERVER WHERE SERVER_ID = ?1 AND SERVER_OWNER = ?2",
        nativeQuery = true)
    ServerEntity findByServerIdAndServerOwner(String serverId, String serverOwner);

    // 이미 가입한 서버를 제외한 모든 서버 가져오기
    @Query(value = "SELECT * FROM SERVER WHERE SERVER_ID NOT IN (SELECT SERVER_MEMBER_SERVER FROM SERVER_MEMBER WHERE SERVER_MEMBER_USER = ?1)", 
    nativeQuery = true)
    List<ServerEntity> findAllExcludeJoined(String appuserId);
}
