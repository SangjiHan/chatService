package com.app.chatservice.wscontrollers;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import com.app.chatservice.services.GroupMessageService;
import com.app.chatservice.superentities.DisplayMessage;

@RestController
public class GroupChatWSController {
    
    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private GroupMessageService service;

    @MessageMapping("/gcmessagesrc/{groupId}/{messageId}")
    @SendTo("/messagedest/gcmessagedest/{groupId}")
    public DisplayMessage sendGCMessage(@DestinationVariable String groupId,
     @DestinationVariable String messageId){
        logger.info("groupId: " + groupId);
        logger.info("messageId: " + messageId);

        DisplayMessage result = service.findById(messageId);
        return result;
     }
}
