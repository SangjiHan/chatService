package com.app.chatservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "CHANNEL_AUTH")
public class ChannelAuthEntity {

    @EmbeddedId
    private ChannelAuthId channelAuthId;
	
	@Column(name = "CHANNEL_AUTH_ACCESSIBLE", nullable = false)
    private int channelAuthAccessible = 0;

    public ChannelAuthId getChannelAuthId() {
		return channelAuthId;
	}
	public ChannelAuthId setChannelAuthId() {
		return channelAuthId;
	}
	public int getChannelAuthAccessible() {
		return channelAuthAccessible;
	}
	public void setChannelAuthAccessible(int channelAuthAccessible) {
		this.channelAuthAccessible = channelAuthAccessible;
	}
}
