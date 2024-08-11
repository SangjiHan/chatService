package com.app.chatservice.superentities;

import java.util.List;

import com.app.chatservice.entities.MessageEntity;
import com.app.chatservice.entities.MessageMediaEntity;

public class Message {
    
    private MessageEntity messageEntity;
    private List<MessageMediaEntity> messageMediaEntity;

    public MessageEntity getMessageEntity() {
        return messageEntity;
    }
    public void setMessageEntity(MessageEntity messageEntity) {
        this.messageEntity = messageEntity;
    }
    public List<MessageMediaEntity> getMessageMediaEntity() {
        return messageMediaEntity;
    }
    public void setMessageMediaEntity(List<MessageMediaEntity> messageMediaEntity) {
        this.messageMediaEntity = messageMediaEntity;
    }



}
