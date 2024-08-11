package com.app.chatservice.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class GroupChatUserId implements Serializable {

    @Column(name = "GROUP_CHAT_USER")
    private String groupChatUser;

    @Column(name = "GROUP_CHAT_USER_GROUP")
    private String groupChatUserGroup;

    public GroupChatUserId() {}
    public GroupChatUserId(String groupChatUser, String groupChatUserGroup) {
        this.groupChatUser = groupChatUser;
        this.groupChatUserGroup = groupChatUserGroup;
    }

    public String getGroupChatUser() {
        return groupChatUser;
    }
    public void setGroupChatUser(String groupChatUser) {
        this.groupChatUser = groupChatUser;
    }
    public String getGroupChatUserGroup() {
        return groupChatUserGroup;
    }
    public void setGroupChatUserGroup(String groupChatUserGroup) {
        this.groupChatUserGroup = groupChatUserGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupChatUserId groupChatUserId = (GroupChatUserId) o;
        return Objects.equals(groupChatUser, groupChatUserId.groupChatUser) && 
                Objects.equals(groupChatUserGroup, groupChatUserId.groupChatUserGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupChatUser, groupChatUserGroup);
    }
}
