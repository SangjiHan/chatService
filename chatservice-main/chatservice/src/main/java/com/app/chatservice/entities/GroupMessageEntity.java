package com.app.chatservice.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "GROUP_MESSAGE")
public class GroupMessageEntity {

    @Id
    private String groupMessageId;
    private String groupMessageUser;
    private String groupMessageContent;
    private String groupMessageGroup;
    private Date groupMessageRegDate;
    private String groupMessageReplyTo;
    private int groupMessageEdited;
    private int groupMessagePing;
    private String groupMessageName;
    private String groupMessagePfp;

    public String getGroupMessageId() {
        return groupMessageId;
    }
    public void setGroupMessageId(String groupMessageId) {
        this.groupMessageId = groupMessageId;
    }
    public String getGroupMessageUser() {
        return groupMessageUser;
    }
    public void setGroupMessageUser(String groupMessageUser) {
        this.groupMessageUser = groupMessageUser;
    }
    public String getGroupMessageContent() {
        return groupMessageContent;
    }
    public void setGroupMessageContent(String groupMessageContent) {
        this.groupMessageContent = groupMessageContent;
    }
    public String getGroupMessageGroup() {
        return groupMessageGroup;
    }
    public void setGroupMessageGroup(String groupMessageGroup) {
        this.groupMessageGroup = groupMessageGroup;
    }
    public Date getGroupMessageRegDate() {
        return groupMessageRegDate;
    }
    public void setGroupMessageRegDate(Date groupMessageRegDate) {
        this.groupMessageRegDate = groupMessageRegDate;
    }
    public String getGroupMessageReplyTo() {
        return groupMessageReplyTo;
    }
    public void setGroupMessageReplyTo(String groupMessageReplyTo) {
        this.groupMessageReplyTo = groupMessageReplyTo;
    }
    public int getGroupMessageEdited() {
        return groupMessageEdited;
    }
    public void setGroupMessageEdited(int groupMessageEdited) {
        this.groupMessageEdited = groupMessageEdited;
    }
    public int getGroupMessagePing() {
        return groupMessagePing;
    }
    public void setGroupMessagePing(int groupMessagePing) {
        this.groupMessagePing = groupMessagePing;
    }
    public String getGroupMessageName() {
        return groupMessageName;
    }
    public void setGroupMessageName(String groupMessageName) {
        this.groupMessageName = groupMessageName;
    }
    public String getGroupMessagePfp() {
        return groupMessagePfp;
    }
    public void setGroupMessagePfp(String groupMessagePfp) {
        this.groupMessagePfp = groupMessagePfp;
    }
    @Override
    public String toString() {
        return "GroupMessageEntity [groupMessageId=" + groupMessageId + ", groupMessageUser=" + groupMessageUser
                + ", groupMessageContent=" + groupMessageContent + ", groupMessageGroup=" + groupMessageGroup
                + ", groupMessageRegDate=" + groupMessageRegDate + ", groupMessageReplyTo=" + groupMessageReplyTo
                + ", groupMessageEdited=" + groupMessageEdited + ", groupMessagePing=" + groupMessagePing
                + ", groupMessageName=" + groupMessageName + ", groupMessagePfp=" + groupMessagePfp + "]";
    }

    
    
}
