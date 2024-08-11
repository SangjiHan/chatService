package com.app.chatservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.chatservice.entities.GroupMessageEntity;

public interface GroupMessageRepository extends JpaRepository<GroupMessageEntity, String>{

    @Query(value = "SELECT * FROM GROUP_MESSAGE WHERE GROUP_MESSAGE_GROUP = ?1"+ 
    " AND ROWNUM <= ?2 ORDER BY GROUP_MESSAGE_REG_DATE ASC", nativeQuery = true)
    List<GroupMessageEntity> findLatest(String gorupId, int messageNumber);

    @Query(value = "SELECT * FROM (SELECT * FROM GROUP_MESSAGE WHERE GROUP_MESSAGE_GROUP = ?1 "+ 
    " AND GROUP_MESSAGE_REG_DATE < (SELECT MESSAGE_REG_DATE FROM MESSAGE WHERE MESSAGE_ID = ?2)" + 
    " ) WHERE ROWNUM <= ?3", nativeQuery = true)
    List<GroupMessageEntity> findOlder(String gorupId, String oldestMessageId, int messageNumber);


}
