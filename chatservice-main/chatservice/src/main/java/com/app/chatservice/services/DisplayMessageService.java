package com.app.chatservice.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.app.chatservice.entities.DisplayMessageEntity;
import com.app.chatservice.entities.GroupMessageEntity;
import com.app.chatservice.entities.MessageMediaEntity;
import com.app.chatservice.repositories.DisplayMessageRepository;
import com.app.chatservice.superentities.DisplayMessage;

@Service
public class DisplayMessageService {

    @Autowired
    private DisplayMessageRepository repository;

    @Autowired
    private MessageMediaService messageMediaService;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupMessageService groupMessageService;

    @Value("${message.number}")
    private int messageNumber;

    private Logger logger = Logger.getLogger(getClass().getName());

    public List<DisplayMessageEntity> findAll() {
        List<DisplayMessageEntity> entities = null;
        try {
           entities = repository.findAll(); 
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entities;
    }

    public Object findLatest(String channelId) { // 최근 몇개의 메세지 가져오기
        List<DisplayMessageEntity> entities = null;
        List<DisplayMessage> displayMessages = new ArrayList<>();

        try {
            entities = repository.findLatest(channelId, messageNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(DisplayMessageEntity displayMessageEntity : entities){
            DisplayMessage displayMessage = new DisplayMessage();

            if(displayMessageEntity.getDisplayMessagePfp() == null){ // 서버 프사 가 없다면 전체 프사로 바꾸기
                String appuserGenPfp =
                 userService.findUserGenPfpById(displayMessageEntity.getDisplayMessageUser());
                displayMessageEntity.setDisplayMessagePfp(appuserGenPfp);
            }

            displayMessage.setDisplayMessageEntity(displayMessageEntity);

            //미디어 리스트 가져오기
            List<MessageMediaEntity> messageMediaEntities = messageMediaService.findByMessage(displayMessageEntity.getDisplayMessageId());
            displayMessage.setMessageMediaEntities(messageMediaEntities);

            displayMessages.add(displayMessage);
        }

        return displayMessages;
    }

    public Object findOlder(String channelId, String oldestMessageId) {
        List<DisplayMessageEntity> displayMessageEntities = null;
        List<DisplayMessage> displayMessages = new ArrayList<>();

        try {
            displayMessageEntities = repository.findOlder(channelId, oldestMessageId, messageNumber);

            for(DisplayMessageEntity displayMessageEntity : displayMessageEntities){
                logger.info(displayMessageEntity.getDisplayMessageId() + "\n");

                DisplayMessage displayMessage = new DisplayMessage();
                displayMessage.setDisplayMessageEntity(displayMessageEntity);

                String messageId = displayMessageEntity.getDisplayMessageId();
                List<MessageMediaEntity> messageMediaEntities = messageMediaService.findByMessage(messageId);
                displayMessage.setMessageMediaEntities(messageMediaEntities);
                
                displayMessages.add(displayMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        return displayMessages;

    }

    public Object findById(String messageId) {
        DisplayMessage displayMessage = new DisplayMessage();

        try {
            DisplayMessageEntity displayMessageEntity = repository.findById(messageId).get();

            if(displayMessageEntity.getDisplayMessagePfp() == null){ // 서버 프사 가 없다면 전체 프사로 바꾸기
                String appuserGenPfp = userService.findUserGenPfpById(displayMessageEntity.getDisplayMessageUser());
                displayMessageEntity.setDisplayMessagePfp(appuserGenPfp);
            }

            List<MessageMediaEntity> messageMediaEntities = messageMediaService.findByMessage(messageId);
            
            displayMessage.setDisplayMessageEntity(displayMessageEntity);
            displayMessage.setMessageMediaEntities(messageMediaEntities);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return displayMessage;
    }

    public DisplayMessageEntity findEitherById(String messageId) {
        DisplayMessageEntity displayMessageEntity = null;

        try {
            displayMessageEntity = repository.findById(messageId).get();
        } catch (Exception e) {
            logger.info("NOVAL");
            GroupMessageEntity groupMessageEntity = groupMessageService.findGroupMessageById(messageId);
            displayMessageEntity = groupMessageService.convertToDisplayMessageEntity(groupMessageEntity);
        }

        return displayMessageEntity; 
    }


}
