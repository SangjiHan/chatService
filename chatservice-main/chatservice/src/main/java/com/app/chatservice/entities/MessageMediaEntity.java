package com.app.chatservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MESSAGE_MEDIA")
public class MessageMediaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "MESSAGE_MEDIA_ID")
    private String messageMediaId;

    @Column(name = "MESSAGE_MEDIA_MESSAGE")
    private String messageMediaMessage;

    @Column(name = "MESSAGE_MEDIA_FILE_NAME")
    private String messageMediaFileName;

    @Column(name = "MESSAGE_MEDIA_TYPE")
    private String messageMediaType;

    @Column(name = "MESSAGE_MEDIA_ORDER")
    private int messageMediaOrder;

    public String getMessageMediaId() {
        return this.messageMediaId;
    }
    public void setMessageMediaId(String messageMediaId) {
        this.messageMediaId = messageMediaId;
    }
    public String getMessageMediaMessage() {
        return this.messageMediaMessage;
    }
    public void setMessageMediaMessage(String messageMediaMessage) {
        this.messageMediaMessage = messageMediaMessage;
    }
    public String getMessageMediaFileName() {
        return this.messageMediaFileName;
    }
    public void setMessageMediaFileName(String messageMediaFileName) {
        this.messageMediaFileName = messageMediaFileName;
    }
    public String getMessageMediaType() {
        return messageMediaType;
    }
    public void setMessageMediaType(String messageMediaType) {
        this.messageMediaType = messageMediaType;
    }
    public int getMessageMediaOrder() {
        return this.messageMediaOrder;
    }
    public void setMessageMediaOrder(int messageMediaOrder) {
        this.messageMediaOrder = messageMediaOrder;
    }


    @Override
    public String toString() {
        return "MessageMediaEntity [messageMediaId=" + messageMediaId + ", messageMediaMessage=" + messageMediaMessage
                + ", messageMediaFileName=" + messageMediaFileName + ", messageMediaOrder=" + messageMediaOrder + "]";
    }


}
