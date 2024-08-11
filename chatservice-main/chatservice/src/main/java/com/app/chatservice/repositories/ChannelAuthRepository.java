package com.app.chatservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.app.chatservice.entities.ChannelAuthEntity;
import com.app.chatservice.entities.ChannelAuthId;

public interface ChannelAuthRepository extends JpaRepository<ChannelAuthEntity, ChannelAuthId>{

    Optional<ChannelAuthEntity> findById(ChannelAuthId channelAuthId);
    List<ChannelAuthEntity> findByChannelAuthAccessible(int channelAuthAccessible);
}
