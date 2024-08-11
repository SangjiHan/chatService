package com.app.chatservice.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "GROUP_CHAT_USER")
public class GroupChatUserEntity {

    @EmbeddedId
    private GroupChatUserId groupChatUserId;

    public GroupChatUserId getGroupChatUserId() {
        return groupChatUserId;
    }
    public void setGroupChatUserId(GroupChatUserId groupChatUserId) {
        this.groupChatUserId = groupChatUserId;
    }
}
