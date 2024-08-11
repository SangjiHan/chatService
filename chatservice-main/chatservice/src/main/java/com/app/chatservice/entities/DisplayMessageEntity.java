package com.app.chatservice.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.SecondaryTables;
import jakarta.persistence.Table;

@Entity
@Table(name = "DISPLAY_MESSAGE")
public class DisplayMessageEntity {
    
    
    @Id
    private String displayMessageId;
    private String displayMessageUser;
    private String displayMessageContent;
    private Date displayMessageRegDate;
    private String displayMessageServer;
    private String displayMessageUserName;
    private String displayMessagePfp;
    private String displayMessageChannel;
    private String displayMessageGroupChat;
    private int displayMessageEdited = 0;
    private String displayMessageReplyTo;
    private int displayMessagePing = 0;

    public String getDisplayMessageId() {
        return displayMessageId;
    }
    public void setDisplayMessageId(String displayMessageId) {
        this.displayMessageId = displayMessageId;
    }
    public String getDisplayMessageUser() {
        return displayMessageUser;
    }
    public void setDisplayMessageUser(String displayMessageUser) {
        this.displayMessageUser = displayMessageUser;
    }
    public String getDisplayMessageContent() {
        return displayMessageContent;
    }
    public void setDisplayMessageContent(String displayMessageContent) {
        this.displayMessageContent = displayMessageContent;
    }
    public Date getDisplayMessageRegDate() {
        return displayMessageRegDate;
    }
    public void setDisplayMessageRegDate(Date displayMessageRegDate) {
        this.displayMessageRegDate = displayMessageRegDate;
    }
    public String getDisplayMessageServer() {
        return displayMessageServer;
    }
    public void setDisplayMessageServer(String displayMessageServer) {
        this.displayMessageServer = displayMessageServer;
    }
    public String getDisplayMessageUserName() {
        return displayMessageUserName;
    }
    public void setDisplayMessageUserName(String displayMessageUserName) {
        this.displayMessageUserName = displayMessageUserName;
    }
    public String getDisplayMessagePfp() {
        return displayMessagePfp;
    }
    public void setDisplayMessagePfp(String displayMessagePfp) {
        this.displayMessagePfp = displayMessagePfp;
    }
    public String getDisplayMessageChannel() {
        return displayMessageChannel;
    }
    public void setDisplayMessageChannel(String displayMessageChannel) {
        this.displayMessageChannel = displayMessageChannel;
    }
    public String getDisplayMessageGroupChat() {
        return displayMessageGroupChat;
    }
    public void setDisplayMessageGroupChat(String displayMessageGroupChat) {
        this.displayMessageGroupChat = displayMessageGroupChat;
    }
    public int getDisplayMessageEdited() {
        return displayMessageEdited;
    }
    public void setDisplayMessageEdited(int displayMessageEdited) {
        this.displayMessageEdited = displayMessageEdited;
    }
    public String getDisplayMessageReplyTo() {
        return displayMessageReplyTo;
    }
    public void setDisplayMessageReplyTo(String displayMessageReplyTo) {
        this.displayMessageReplyTo = displayMessageReplyTo;
    }
    public int getDisplayMessagePing() {
        return displayMessagePing;
    }
    public void setDisplayMessagePing(int displayMessagePing) {
        this.displayMessagePing = displayMessagePing;
    }

    @Override
    public String toString() {
        return "DisplayMessageEntity [displayMessageId=" + displayMessageId + ", displayMessageUser="
                + displayMessageUser + ", displayMessageContent=" + displayMessageContent + ", displayMessageRegDate="
                + displayMessageRegDate + ", displayMessageServer=" + displayMessageServer + ", displayMessageUserName="
                + displayMessageUserName + ", displayMessagePfp=" + displayMessagePfp + ", displayMessageChannel="
                + displayMessageChannel + ", displayMessageGroupChat=" + displayMessageGroupChat
                + ", displayMessageEdited=" + displayMessageEdited + ", displayMessageReplyTo=" + displayMessageReplyTo
                + ", displayMessagePing=" + displayMessagePing + "]";
    }

    
}
