package com.app.chatservice.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "FRIEND_REQUEST")
public class FriendRequestEntity {
    
    @EmbeddedId
    private FriendRequestId friendRequestId;
    
    public FriendRequestId getFriendRequestId() {
        return friendRequestId;
    }
    public void setFriendRequestId(FriendRequestId friendRequestId) {
        this.friendRequestId = friendRequestId;
    }


}
