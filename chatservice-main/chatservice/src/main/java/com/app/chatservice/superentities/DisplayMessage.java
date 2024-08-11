package com.app.chatservice.superentities;

import java.util.List;

import com.app.chatservice.entities.DisplayMessageEntity;
import com.app.chatservice.entities.MessageMediaEntity;

public class DisplayMessage {
    private DisplayMessageEntity displayMessageEntity;
    private List<MessageMediaEntity> messageMediaEntities;

    public List<MessageMediaEntity> getMessageMediaEntities() {
        return messageMediaEntities;
    }

    public void setMessageMediaEntities(List<MessageMediaEntity> messageMediaEntities) {
        this.messageMediaEntities = messageMediaEntities;
    }

    public DisplayMessageEntity getDisplayMessageEntity() {
        return displayMessageEntity;
    }

    public void setDisplayMessageEntity(DisplayMessageEntity displayMessageEntity) {
        this.displayMessageEntity = displayMessageEntity;
    }

}
