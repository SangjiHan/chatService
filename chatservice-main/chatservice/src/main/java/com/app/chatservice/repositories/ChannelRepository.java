package com.app.chatservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

import com.app.chatservice.entities.ChannelEntity;

public interface ChannelRepository extends JpaRepository<ChannelEntity, String> {

    // ChannelId로 채널 찾기
    ChannelEntity findByChannelId(String channelId);
    // ChannelName으로 채널 모두 찾기
    List<ChannelEntity> findByChannelName(String channelName);
    // ChannelServer로 채널 모두 찾기

    @Query(value = "SELECT * FROM CHANNEL WHERE CHANNEL_SERVER = ?1 ORDER BY CHANNEL_REG_DATE ASC",
    nativeQuery = true)
    List<ChannelEntity> findByChannelServer(String channelServer);
    // ChannelRegDate로 채널 모두 찾기
    List<ChannelEntity> findByChannelRegDate(Date channelRegDate);

    @Query(value = "SELECT CHANNEL_ID FROM (SELECT * FROM CHANNEL WHERE CHANNEL_SERVER = ?1)" + 
    " WHERE CHANNEL_REG_DATE = (SELECT MIN(CHANNEL_REG_DATE) FROM CHANNEL WHERE CHANNEL_SERVER = ?1)",
    nativeQuery = true)
    String findDefaultChannel(String serverId);

    /*@Query(value = "SELECT CHANNEL_ID FROM CHANNEL WHERE CHANNEL_REG_DATE = (SELECT MIN(CHANNEL_REG_DATE) FROM CHANNEL WHERE CHANNEL_SERVER = ?1)",
        nativeQuery = true)
    String featuredChannel(String serverId); */
}
