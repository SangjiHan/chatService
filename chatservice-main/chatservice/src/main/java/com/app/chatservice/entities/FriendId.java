package com.app.chatservice.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FriendId implements Serializable {

    @Column(name = "FRIEND1")
    private String friend1;

    @Column(name = "FRIEND2")
    private String friend2;

    // 생성자
    public FriendId() {};
    public FriendId(String friend1, String friend2) {
        this.friend1 = friend1;
        this.friend2 = friend2;
    }

    public String getFriend1() {
        return friend1;
    }
    public void setFriend1(String friend1) {
        this.friend1 = friend1;
    }
    public String getFriend2() {
        return friend2;
    }
    public void setFriend2(String friend2) {
        this.friend2 = friend2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendId friendId = (FriendId) o;
        return Objects.equals(friend1, friendId.friend1) && 
                Objects.equals(friend2, friendId.friend2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(friend1, friend2);
    }
}
