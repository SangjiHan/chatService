package com.app.chatservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;
import com.app.chatservice.entities.ReactionLogEntity;
import com.app.chatservice.entities.ReactionLogId;

public interface ReactionLogRepository extends JpaRepository<ReactionLogEntity, ReactionLogId> {

    Optional<ReactionLogEntity> findById(ReactionLogId reactionLogId);
    List<ReactionLogEntity> findByReactionLogId_ReactionLogReaction(String reactionLogReaction);
    List<ReactionLogEntity> findByReactionLogId_ReactionLogMessage(String reactionLogMessage);
    List<ReactionLogEntity> findByReactionLogId_ReactionLogReactor(String reactionLogReactor);
}
