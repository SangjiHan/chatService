package com.app.chatservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.app.chatservice.entities.DisplayMessageEntity;
import com.app.chatservice.entities.GroupMessageEntity;
import com.app.chatservice.entities.MessageMediaEntity;
import com.app.chatservice.repositories.GroupMessageRepository;
import com.app.chatservice.superentities.DisplayMessage;

@Service
public class GroupMessageService {

    @Autowired
    private GroupMessageRepository repository;

    @Autowired
    private MessageMediaService messageMediaService;

    private Logger logger = Logger.getLogger(getClass().getName());

    @Value("${message.number}")
    private int messageNumber;

    public List<DisplayMessage> findLatest(String gorupId) {
        
        List<GroupMessageEntity> groupMessageEntities = repository.findLatest(gorupId, messageNumber);
        List<DisplayMessage> displayMessages = new ArrayList<>();
        for(GroupMessageEntity groupMessageEntity : groupMessageEntities){

            DisplayMessageEntity displayMessageEntity = convertToDisplayMessageEntity(groupMessageEntity);

            List<MessageMediaEntity> messageMediaEntities = 
            messageMediaService.findByMessage(displayMessageEntity.getDisplayMessageId());

            DisplayMessage displayMessage = new DisplayMessage();
            displayMessage.setDisplayMessageEntity(displayMessageEntity);
            displayMessage.setMessageMediaEntities(messageMediaEntities);

            displayMessages.add(displayMessage);
        }

        return displayMessages;
    }

    public List<DisplayMessage> findOlder(String groupId, String oldestMessageId) {
        List<GroupMessageEntity> groupMessageEntities = repository.findOlder(groupId, oldestMessageId, messageNumber);
        List<DisplayMessage> displayMessages = new ArrayList<>();
        for(GroupMessageEntity groupMessageEntity : groupMessageEntities){
            DisplayMessageEntity displayMessageEntity = convertToDisplayMessageEntity(groupMessageEntity);
            DisplayMessage displayMessage = new DisplayMessage();
            displayMessage.setDisplayMessageEntity(displayMessageEntity);

            List<MessageMediaEntity> messageMediaEntities =
             messageMediaService.findByMessage(displayMessageEntity.getDisplayMessageId());
            displayMessage.setMessageMediaEntities(messageMediaEntities);

            displayMessages.add(displayMessage);
        }
        return displayMessages;
    }

    public DisplayMessage findById(String messageId) {

        GroupMessageEntity groupMessageEntity = repository.findById(messageId).get();
        logger.info(groupMessageEntity.toString());

        List<MessageMediaEntity> messageMediaEntities = messageMediaService.findByMessage(messageId);
        DisplayMessageEntity displayMessageEntity = convertToDisplayMessageEntity(groupMessageEntity);

        DisplayMessage displayMessage = new DisplayMessage();
        displayMessage.setDisplayMessageEntity(displayMessageEntity);
        displayMessage.setMessageMediaEntities(messageMediaEntities);
        
        return displayMessage;
    }

    public GroupMessageEntity findGroupMessageById(String messageId){

        GroupMessageEntity groupMessageEntity = repository.findById(messageId).get();
        return groupMessageEntity;
    }

    public DisplayMessageEntity convertToDisplayMessageEntity(GroupMessageEntity groupMessageEntity){
        DisplayMessageEntity displayMessageEntity = new DisplayMessageEntity();

        displayMessageEntity.setDisplayMessageId(groupMessageEntity.getGroupMessageId());
        displayMessageEntity.setDisplayMessageUser(groupMessageEntity.getGroupMessageUser());
        displayMessageEntity.setDisplayMessageContent(groupMessageEntity.getGroupMessageContent());
        displayMessageEntity.setDisplayMessageRegDate(groupMessageEntity.getGroupMessageRegDate());
        displayMessageEntity.setDisplayMessageUserName(groupMessageEntity.getGroupMessageName());
        displayMessageEntity.setDisplayMessagePfp(groupMessageEntity.getGroupMessagePfp());
        displayMessageEntity.setDisplayMessageGroupChat(groupMessageEntity.getGroupMessageGroup());
        displayMessageEntity.setDisplayMessageEdited(groupMessageEntity.getGroupMessageEdited());
        displayMessageEntity.setDisplayMessageReplyTo(groupMessageEntity.getGroupMessageReplyTo());
        displayMessageEntity.setDisplayMessagePing(groupMessageEntity.getGroupMessagePing());

        return displayMessageEntity;

    }



}
