package com.app.chatservice.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FriendRequestId implements Serializable {

    @Column(name = "FRIEND_REQUEST_SENDER")
    private String friendRequestSender;

    @Column(name = "FRIEND_REQUEST_RECEIVER")
    private String friendRequestReceiver;

    public FriendRequestId() {}
    public FriendRequestId(String friendRequestSender, String friendRequestReceiver) {
        this.friendRequestSender = friendRequestSender;
        this.friendRequestReceiver = friendRequestReceiver;
    }

    public String getFriendRequestSender() {
        return friendRequestSender;
    } 
    public void setFriendRequestSender(String friendRequestSender) {
        this.friendRequestSender = friendRequestSender;
    }
    public String getFriendRequestReceiver() {
        return friendRequestReceiver;
    } 
    public void setFriendRequestReceiver(String friendRequestReceiver) {
        this.friendRequestReceiver = friendRequestReceiver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendRequestId friendRequestId = (FriendRequestId) o;
        return Objects.equals(friendRequestSender, friendRequestId.friendRequestSender) && 
                Objects.equals(friendRequestReceiver, friendRequestId.friendRequestReceiver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(friendRequestSender, friendRequestReceiver);
    }
    @Override
    public String toString() {
        return "FriendRequestId [friendRequestSender=" + friendRequestSender + ", friendRequestReceiver="
                + friendRequestReceiver + "]";
    }


}
