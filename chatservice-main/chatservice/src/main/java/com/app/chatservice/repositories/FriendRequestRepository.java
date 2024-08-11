package com.app.chatservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.chatservice.entities.FriendEntity;
import com.app.chatservice.entities.FriendRequestEntity;
import com.app.chatservice.entities.FriendRequestId;

public interface FriendRequestRepository extends JpaRepository<FriendRequestEntity, FriendRequestId> {

    Optional<FriendRequestEntity> findById(FriendRequestId friendRequestId);
    List<FriendRequestEntity> findByFriendRequestId_FriendRequestSender(String friendRequestSender);
    List<FriendRequestEntity> findByFriendRequestId_FriendRequestReceiver(String friendRequestReceiver);
}
