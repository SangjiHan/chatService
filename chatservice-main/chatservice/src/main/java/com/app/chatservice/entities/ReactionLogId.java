package com.app.chatservice.entities;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ReactionLogId implements Serializable {

    @Column(name = "REACTION_LOG_REACTION")
    private String reactionLogReaction;

    @Column(name = "REACTION_LOG_MESSAGE")
    private String reactionLogMessage;

    @Column(name = "REACTION_LOG_REACTOR")
    private String reactionLogReactor;

    public ReactionLogId() {}
    public ReactionLogId(String reactionLogReaction, String reactionLogMessage, String reactionLogReactor) {
        this.reactionLogReaction = reactionLogReaction;
        this.reactionLogMessage = reactionLogMessage;
        this.reactionLogReactor = reactionLogReactor;
    }

    public String getReactionLogReaction() {
        return reactionLogReaction;
    }

    public void setReactionLogReaction(String reactionLogReaction) {
        this.reactionLogReaction = reactionLogReaction;
    }

    public String getReactionLogMessage() {
        return reactionLogMessage;
    }

    public void setReactionLogMessage(String reactionLogMessage) {
        this.reactionLogMessage = reactionLogMessage;
    }

    public String getReactionLogReactor() {
        return reactionLogReactor;
    }
    
    public void setReactionLogReactor(String reactionLogReactor) {
        this.reactionLogReactor = reactionLogReactor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReactionLogId reactionLogId = (ReactionLogId) o;
        return Objects.equals(reactionLogReaction, reactionLogId.reactionLogReaction) && 
                Objects.equals(reactionLogMessage, reactionLogId.reactionLogMessage) && 
                Objects.equals(reactionLogReactor, reactionLogId.reactionLogReactor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reactionLogReaction, reactionLogMessage, reactionLogReactor);
    }
    @Override
    public String toString() {
        return "ReactionLogId [reactionLogReaction=" + reactionLogReaction + ", reactionLogMessage="
                + reactionLogMessage + ", reactionLogReactor=" + reactionLogReactor + "]";
    }


}
