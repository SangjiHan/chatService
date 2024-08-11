package com.app.chatservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.chatservice.entities.FriendEntity;
import com.app.chatservice.entities.FriendId;

public interface FriendRepository extends JpaRepository<FriendEntity, FriendId> {

    // 사용자1과 사용자2가 친구 관계인지 확인
    Optional<FriendEntity> findById(FriendId friend);
    // 사용자1과 친구 관계인 모든 사용자 확인
    List<FriendEntity> findByFriend_Friend1(String friend1);
}
