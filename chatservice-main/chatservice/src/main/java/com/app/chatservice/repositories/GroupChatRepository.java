package com.app.chatservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.app.chatservice.entities.GroupChatEntity;

public interface GroupChatRepository extends JpaRepository<GroupChatEntity, String>{

    GroupChatEntity findByGroupChatId(String groupChatId);
    List<GroupChatEntity> findByGroupChatCreator(String groupChatCreator);
    List<GroupChatEntity> findByGroupChatName(String groupChatName);

    @Query(value = "SELECT GROUP_CHAT_ID FROM GROUP_CHAT WHERE GROUP_CHAT_CREATOR = ?1", nativeQuery = true)
    List<String> findGroupChatIdsByCreator(String appuserId);
}
