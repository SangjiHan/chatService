package com.app.chatservice.entities;

import org.hibernate.annotations.Check;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "MESSAGE")
@Check(constraints = "((MESSAGE_CHANNEL IS NULL AND MESSAGE_GROUP_CHAT IS NOT NULL) OR (MESSAGE_CHANNEL IS NOT NULL AND MESSAGE_GROUP_CHAT IS NULL))")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "MESSAGE_ID")
    private String messageId;

    @Column(name = "MESSAGE_CHANNEL")
    private String messageChannel;

    @Column(name = "MESSAGE_GROUP_CHAT")
    private String messageGroupChat;

    @Column(name = "MESSAGE_USER")
    private String messageUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MESSAGE_REG_DATE")
    private Date messageRegDate;
    
    @Column(name = "MESSAGE_CONTENT")
    private String messageContent;

    @Column(name = "MESSAGE_EDITED")
    private int messageEdited = 0;

    @Column(name = "MESSAGE_REPLY_TO", nullable = true)
    private String messageReplyTo;

    @Column(name = "MESSAGE_PING")
    private int messagePing = 0;

    @PrePersist
    public void onCreate() {
        this.messageRegDate = new Date();
    }

    @Override
    public String toString() {
        return "MessageEntity [messageId=" + messageId + ", messageChannel=" + messageChannel + ", messageGroupChat="
                + messageGroupChat + ", messageUser=" + messageUser + ", messageRegDate=" + messageRegDate
                + ", messageContent=" + messageContent + ", messageEdited=" + messageEdited + ", messageReplyTo="
                + messageReplyTo + ", messagePing=" + messagePing + "]";
    }

    public String getMessageId() {
        return messageId;
    }
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
    public String getMessageChannel() {
        return messageChannel;
    }
    public void setMessageChannel(String messageChannel) {
        this.messageChannel = messageChannel;
    }
    public String getMessageGroupChat() {
        return messageGroupChat;
    }
    public void setMessageGroupChat(String messageGroupChat) {
        this.messageGroupChat = messageGroupChat;
    }
    public String getMessageUser() {
        return messageUser;
    }
    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }
    public Date getMessageRegDate() {
        return messageRegDate;
    }
    public void setMessageRegDate(Date messageRegDate) {
        this.messageRegDate = messageRegDate;
    }
    public String getMessageContent() {
        return messageContent;
    }
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
    public int getMessageEdited() {
        return messageEdited;
    }
    public void setMessageEdited(int messageEdited) {
        this.messageEdited = messageEdited;
    }
    public String getMessageReplyTo() {
        return messageReplyTo;
    }
    public void setMessageReplyTo(String messageReplyTo) {
        this.messageReplyTo = messageReplyTo;
    }
    public int getMessagePing() {
        return messagePing;
    }
    public void setMessagePing(int messagePing) {
        this.messagePing = messagePing;
    }
}
