package com.app.chatservice.repositories;

import java.util.List;
import java.util.Date;

// import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.chatservice.entities.MessageEntity;

import jakarta.transaction.Transactional;

public interface MessageRepository extends JpaRepository<MessageEntity, String> {

    MessageEntity findByMessageId(String messageId);
    List<MessageEntity> findByMessageChannel(String messageChannel);
    List<MessageEntity> findByMessageGroupChat(String messageGroupChat);
    List<MessageEntity> findByMessageUser(String messageUser);
    List<MessageEntity> findByMessageRegDate(Date messageRegDate);
    List<MessageEntity> findByMessageContent(String messageContent);
    List<MessageEntity> findByMessageEdited(int messageEdited);
    List<MessageEntity> findByMessageReplyTo(String messageReplyTo);
    List<MessageEntity> findByMessagePing(int messagePing);

    /* 
        // 생성된 MessageEntity 객체 모두 오름차순 정렬 후 미리 가져오기
        @Query("SELECT me FROM MessageEntity me WHERE me.messageChannel = :messageChannel ORDER BY me.messageRegDate ASC")
        List<MessageEntity> findByMessageChannelOrderByMessageRegDateAsc(@Param("messageChannel") String messageChannel);
    */

    // 가장 최근 생성된 MessageEntity 객체 10개만 가져오기
    @Query("SELECT me FROM MessageEntity me WHERE me.messageChannel = :messageChannel ORDER BY me.messageRegDate ASC LIMIT 10")
    List<MessageEntity> findTop10MessageChannelOrderByMessageRegDateAsc(@Param("messageChannel") String messageChannel);

    // 이미 가져온 MessageEntity 객체들 중에서 가장 오래된 객체의 생성일자 : oldestMessageRegDate를 기준으로 더 오래된 객체 10개만 가져오기
    // 실행 전 oldestMessageRegDate를 생성, 할당하고 매번 갱신
    @Query("SELECT me FROM MessageEntity me WHERE me.messageChannel = :messageChannel AND me.messageRegDate < :oldestMessageRegDate ORDER BY me.messageRegDate ASC LIMIT 10")
    List<MessageEntity> findTop10MessageChannelOrderByMessageRegDateAsc(@Param("messageChannel") String messageChannel, @Param("oldestMessageRegDate") Date oldestMessageRegDate);

    @Query(value = "UPDATE MESSAGE SET MESSAGE_REG_DATE = SYSDATE WHERE MESSAGE_ID = ?1", nativeQuery = true)
    @Modifying
    @Transactional
    void updateRegDate(String string);

    @Query(value = "UPDATE MESSAGE SET MESSAGE_CONTENT = ?2 WHERE MESSAGE_ID = ?1",
    nativeQuery = true)
    @Modifying
    @Transactional
    void updateContent(String messageId, String editedContent);


}


