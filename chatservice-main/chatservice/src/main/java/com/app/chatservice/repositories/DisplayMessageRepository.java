package com.app.chatservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.chatservice.entities.DisplayMessageEntity;

public interface DisplayMessageRepository extends JpaRepository<DisplayMessageEntity, String>{

    // 최근 몇개의 메세지 가져옴
    @Query(value = "SELECT * FROM (SELECT * FROM DISPLAY_MESSAGE WHERE DISPLAY_MESSAGE_CHANNEL = ?1 ORDER BY DISPLAY_MESSAGE_REG_DATE DESC) WHERE ROWNUM <= ?2"
    + " ORDER BY DISPLAY_MESSAGE_REG_DATE ASC", 
    nativeQuery = true)
    List<DisplayMessageEntity> findLatest(String channelId, int messageNumber);

    @Query(value = "SELECT * FROM (SELECT * FROM DISPLAY_MESSAGE WHERE DISPLAY_MESSAGE_CHANNEL = ?1" + 
    " AND DISPLAY_MESSAGE_REG_DATE < (SELECT MESSAGE_REG_DATE FROM MESSAGE WHERE MESSAGE_ID = ?2)" + 
    " ORDER BY DISPLAY_MESSAGE_REG_DATE DESC)" + 
    " WHERE ROWNUM <= ?3", nativeQuery = true)
    List<DisplayMessageEntity> findOlder(String channelId, String oldestMessageId, int messageNumber);

}
