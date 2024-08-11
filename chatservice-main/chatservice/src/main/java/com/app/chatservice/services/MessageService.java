package com.app.chatservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.chatservice.entities.MessageEntity;
import com.app.chatservice.entities.MessageMediaEntity;
import com.app.chatservice.repositories.MessageRepository;
import com.app.chatservice.superentities.Message;
import com.app.chatservice.util.FileUploader;

@Service
public class MessageService {

    @Autowired
    private MessageRepository repository;

    @Autowired
    private MessageMediaService messageMediaService;

    @Autowired
    private FileUploader fileUploader;


    private Logger logger = Logger.getLogger(getClass().getName());

    public MessageEntity create(MessageEntity entity) {
        MessageEntity result = null;
        try {
            result = repository.save(entity);
            logger.info(entity.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public Message create(MessageEntity entity, MultipartFile[] files) {

        MessageEntity messageEntity = create(entity);
        List<MessageMediaEntity> messageMediaEntities = null;
        if(files != null){
            messageMediaEntities = (List<MessageMediaEntity>) messageMediaService.create(messageEntity.getMessageId(), files);
        }
        
        repository.updateRegDate(messageEntity.getMessageId());
        Message message = new Message();
        message.setMessageEntity(messageEntity);
        message.setMessageMediaEntity(messageMediaEntities);

        return message;
    }

    public Object delete(String messageId) {

        try {
            repository.deleteById(messageId);
            return messageId;
            
        } catch (Exception e) {
            return "failed";
        }
    }

    @Transactional
    public Object update(String messageId, String editedContent) {

        MessageEntity messageEntity = findById(messageId);
        messageEntity.setMessageContent(editedContent);
        messageEntity.setMessageEdited(1);
        return messageId;
    }

	public MessageEntity findById(String messageId) {

        MessageEntity messageEntity = repository.findById(messageId).get();
        return messageEntity;
	}

}
