package com.app.chatservice.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ChannelAuthId implements Serializable {

    @Column(name = "CHANNEL_AUTH_CHANNEL")
    private String channelAuthChannel;

    @Column(name = "CHANNEL_AUTH_USER")
    private String channelAuthUser;

    // 생성자
    public ChannelAuthId() {};
    public ChannelAuthId(String channelAuthChannel, String channelAuthUser) {
        this.channelAuthChannel = channelAuthChannel;
        this.channelAuthUser = channelAuthUser;
    }

    // Getter & Setter
    public String getChannelAuthId() {
		return channelAuthChannel;
	}
	public void setChannelAuthId(String channelAuthChannel) {
		this.channelAuthChannel = channelAuthChannel;
	}
	public String getChannelAuthUserId() {
		return channelAuthUser;
	}
	public void setChannelAuthUserId(String channelAuthUser) {
		this.channelAuthUser = channelAuthUser;
	}

    // Equals & HashCode 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChannelAuthId authId = (ChannelAuthId) o;
        return Objects.equals(channelAuthChannel, authId.channelAuthChannel) && 
                Objects.equals(channelAuthUser, authId.channelAuthUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(channelAuthChannel, channelAuthUser);
    }
}
