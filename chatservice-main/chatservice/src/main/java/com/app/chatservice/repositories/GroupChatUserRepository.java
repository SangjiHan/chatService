package com.app.chatservice.repositories;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.chatservice.entities.GroupChatUserEntity;
import com.app.chatservice.entities.GroupChatUserId;

public interface GroupChatUserRepository extends JpaRepository<GroupChatUserEntity, GroupChatUserId> {

    Optional<GroupChatUserEntity> findById(GroupChatUserId groupChatUserId);
    List<GroupChatUserEntity> findByGroupChatUserId_GroupChatUser(String groupChatUser);
    List<GroupChatUserEntity> findByGroupChatUserId_GroupChatUserGroup(String groupChatUserGroup);

    @Query(value = "SELECT GROUP_CHAT_USER FROM GROUP_CHAT_USER WHERE GROUP_CHAT_USER_GROUP = ?1", nativeQuery = true)
    List<String> findUsersById(String groupChatId);

    @Query(value = "SELECT GROUP_CHAT_USER_GROUP FROM GROUP_CHAT_USER WHERE GROUP_CHAT_USER = ?1", nativeQuery = true)
    List<String> findGroupChatGroupsByGroupChatUser(String appuserId);

    @Query(value = "SELECT GROUP_CHAT_USER_GROUP FROM GROUP_CHAT_USER WHERE GROUP_CHAT_USER = ?1", nativeQuery = true)
    List<String> findAllByUser(String apppuserId);

    @Query(value = "SELECT GROUP_CHAT_USER FROM GROUP_CHAT_USER WHERE GROUP_CHAT_USER != ?1 AND GROUP_CHAT_USER_GROUP = ?2", nativeQuery = true)
    List<String> findGroupUsersExcludeSelf(String appuserId, String groupId);

}
