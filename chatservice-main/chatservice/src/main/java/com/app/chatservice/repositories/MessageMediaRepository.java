package com.app.chatservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.app.chatservice.entities.MessageMediaEntity;

public interface MessageMediaRepository extends JpaRepository<MessageMediaEntity, String> {

    MessageMediaEntity findByMessageMediaId(String messageMediaId);
    List<MessageMediaEntity> findByMessageMediaMessage(String messageMediaMessage);
    List<MessageMediaEntity> findByMessageMediaFileName(String messageMediaFileName);
    List<MessageMediaEntity> findByMessageMediaOrder(int messageMediaOrder);
}
