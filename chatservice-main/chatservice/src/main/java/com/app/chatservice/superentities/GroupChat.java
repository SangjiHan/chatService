package com.app.chatservice.superentities;


import java.util.List;

import com.app.chatservice.entities.AppUserEntity;

public class GroupChat {

    private AppUserEntity groupCreator;

    private List<AppUserEntity> groupChatUsers;

    public AppUserEntity getGroupCreator() {
        return groupCreator;
    }

    public void setGroupCreator(AppUserEntity groupCreator) {
        this.groupCreator = groupCreator;
    }

    public List<AppUserEntity> getGroupChatUsers() {
        return groupChatUsers;
    }

    public void setGroupChatUsers(List<AppUserEntity> groupChatUsers) {
        this.groupChatUsers = groupChatUsers;
    }

    
}
