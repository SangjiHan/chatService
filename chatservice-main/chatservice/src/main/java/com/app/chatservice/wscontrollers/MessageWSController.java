package com.app.chatservice.wscontrollers;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.chatservice.entities.DisplayMessageEntity;
import com.app.chatservice.entities.MessageEntity;
import com.app.chatservice.services.DisplayMessageService;
import com.app.chatservice.services.MessageService;
import com.app.chatservice.superentities.DisplayMessage;

@RestController
public class MessageWSController {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private DisplayMessageService displayMessageService;

    @Autowired
    private MessageService messageService;

    @MessageMapping("/{channelId}/{messageId}")
    @SendTo("/messagedest/{channelId}")
    public DisplayMessage sendMessage(@DestinationVariable String messageId, @DestinationVariable String channelId) 
    throws InterruptedException{
        logger.info("messageId: " + messageId);
        logger.info("channelId: " + channelId);

        DisplayMessage displayMessage = (DisplayMessage) displayMessageService.findById(messageId);
        return displayMessage;
    }

    @MessageMapping("/delete/{id}/{messageId}")
    @SendTo("/messagedest/delete/{id}")
    public String sendDeleteMessage(@DestinationVariable String messageId,
    @DestinationVariable String id){
        logger.info("DELETE");
        logger.info(messageId);
        return messageId;
    }

    @MessageMapping("/edit/{id}/{messageId}")
    @SendTo("/messagedest/edit/{id}")
    public DisplayMessageEntity sendEditMessage(@DestinationVariable String id,
    @DestinationVariable String messageId){
        logger.info("EDIT");
        logger.info(id);

        DisplayMessageEntity displayMessageEntity = displayMessageService.findEitherById(messageId);

        if(displayMessageEntity == null){
            logger.info("NULL");
        }

        return displayMessageEntity;
    }


}
