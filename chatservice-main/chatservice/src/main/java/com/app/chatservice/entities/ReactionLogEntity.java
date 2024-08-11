package com.app.chatservice.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "REACTION_LOG")
public class ReactionLogEntity {

    @EmbeddedId
    private ReactionLogId reactionLogId;

    public ReactionLogId getReactionLogId() {
        return reactionLogId;
    }
    public void setReactionLogId(ReactionLogId reactionLogId) {
        this.reactionLogId = reactionLogId;
    }
}
