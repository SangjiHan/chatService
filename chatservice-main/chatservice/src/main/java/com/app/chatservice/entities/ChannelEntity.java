package com.app.chatservice.entities;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "CHANNEL", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CHANNEL_NAME", "CHANNEL_SERVER"})
})
public class ChannelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "CHANNEL_ID")
    private String channelId;

	@Column(name = "CHANNEL_NAME")
    private String channelName;

	@Column(name = "CHANNEL_SERVER")
    private String channelServer;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CHANNEL_REG_DATE", nullable = false)
    private Date channelRegDate;
    @PrePersist
    private void onCreate(){
        channelRegDate = new Date();
    }

    public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getChannelServer() {
		return channelServer;
	}
	public void setChannelServer(String channelServer) {
		this.channelServer = channelServer;
	}
	public Date getChannelRegDate() {
		return channelRegDate;
	}
	public void setChannelRegDate(Date channelRegDate) {
		this.channelRegDate = channelRegDate;
	}

	@Override
	public String toString() {
		return "i channelId=" + channelId + ", channelName=" + channelName + ", channelServer="
				+ channelServer + ", channelRegDate=" + channelRegDate + "]";
	}
}
