package com.app.chatservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "GROUP_CHAT")
public class GroupChatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "GROUP_CHAT_ID")
    private String groupChatId;

    @Column(name = "GROUP_CHAT_CREATOR")
    private String groupChatCreator;

    @Column(name = "GROUP_CHAT_NAME")
    private String groupChatName;

    public String getGroupChatId() {
        return groupChatId;
    }
    public void setGroupChatId(String groupChatId) {
        this.groupChatId = groupChatId;
    }
    public String getGroupChatCreator() {
        return groupChatCreator;
    }
    public void setGroupChatCreator(String groupChatCreator) {
        this.groupChatCreator = groupChatCreator;
    }
    public String getGroupChatName() {
        return groupChatName;
    }
    public void setGroupChatName(String groupChatName) {
        this.groupChatName = groupChatName;
    }
    @Override
    public String toString() {
        return "GroupChatEntity [groupChatId=" + groupChatId + ", groupChatCreator=" + groupChatCreator
                + ", groupChatName=" + groupChatName + "]";
    }

    
}
