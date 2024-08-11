package com.app.chatservice.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "FRIEND")
public class FriendEntity {
    
    @EmbeddedId
    private FriendId friend;

    public FriendId getFriend() {
        return friend;
    }
    public void setFriend(FriendId friend) {
        this.friend = friend;
    }
}
