package com.app.chatservice.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "REACTION")
public class ReactionEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "REACTION_ID")
    private String reactionId;

    @Column(name = "REACTION_SERVER")
    private String reactionServer;

    @Column(name = "REACTION_NAME")
    private String reactionName;

    @Column(name = "REACTION_FILE")
    private String reactionFile;

    @Column(name = "REACTION_REG_DATE")
    private Date reactionRegDate;

    @PrePersist
    public void onCreate(){
        this.reactionRegDate = new Date();
    }

    public String getReactionId() {
        return this.reactionId;
    }

    public void setReactionId(String reactionId) {
        this.reactionId = reactionId;
    }
    
    public String getReactionServer() {
        return this.reactionServer;
    }

    public void setReactionServer(String reactionServer) {
        this.reactionServer = reactionServer;
    }

    public String getReactionName() {
        return this.reactionName;
    }
    
    public void setReactionName(String reactionName) {
        this.reactionName = reactionName;
    }

    public String getReactionFile() {
        return this.reactionFile;
    }

    public void setReactionFile(String reactionFile) {
        this.reactionFile = reactionFile;
    }

    public Date getReactionRegDate() {
        return this.reactionRegDate;
    }
    
    public void setReactionRegDate(Date reactionRegDate) {
        this.reactionRegDate = reactionRegDate;
    }
}
