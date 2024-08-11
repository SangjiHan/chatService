package com.app.chatservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Date;

import com.app.chatservice.entities.ServerMemberEntity;
import com.app.chatservice.entities.ServerMemberId;

import jakarta.transaction.Transactional;

public interface ServerMemberRepository extends JpaRepository<ServerMemberEntity, ServerMemberId>{

    // memberUser와 memberServer로 멤버 찾기
    Optional<ServerMemberEntity> findById(ServerMemberId memberId);
    // memberRegDate로 멤버 모두 찾기
    List<ServerMemberEntity> findByServerMemberRegDate(Date serverMemberRegDate);
    // memberUserName으로 멤버 모두 찾기
    List<ServerMemberEntity> findByServerMemberUserName(String serverMemberUserName);
    // memberPfp로 멤버 모두 찾기
    List<ServerMemberEntity> findByServerMemberPfp(String serverMemberPfp);

    List<ServerMemberEntity> findByMemberId_ServerMemberUser(String serverMemberUser);

    @Query("SELECT sme.memberId.serverMemberServer FROM ServerMemberEntity sme WHERE sme.memberId.serverMemberUser = :appuserId")
    List<String> findByServerMemberUserIn(@Param("appuserId") String appuserId);


    @Query(value = "UPDATE SERVER_MEMBER SET SERVER_MEMBER_LAST_VISITED = ?3 WHERE SERVER_MEMBER_SERVER = ?1 AND SERVER_MEMBER_USER = ?2",
    nativeQuery = true)
    @Modifying
    @Transactional
    void updateLastVisited(String serverId, String appuserId, String channelId);

    @Query(value = "SELECT SERVER_MEMBER_LAST_VISITED FROM SERVER_MEMBER WHERE SERVER_MEMBER_SERVER = ?1 AND SERVER_MEMBER_USER = ?2",
    nativeQuery = true)
    String findLastVisitedByUserAndServer(String serverId, String appuserId);

    @Query(value = "SELECT * FROM SERVER_MEMBER WHERE SERVER_MEMBER_SERVER = ?1", 
    nativeQuery = true)
    List<ServerMemberEntity> findAllByServer(String serverId);
}
