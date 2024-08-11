package com.app.chatservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Date;
import com.app.chatservice.entities.ReactionEntity;

public interface ReactionRepository extends JpaRepository<ReactionEntity, String> {

    ReactionEntity findByReactionId(String reactionId);
    List<ReactionEntity> findByReactionServer(String reactionServer);
    List<ReactionEntity> findByReactionName(String reactionName);
    List<ReactionEntity> findByReactionFile(String reactionFile);
    List<ReactionEntity> findByReactionRegDate(Date reactionRegDate);
}
