package com.app.chatservice.restcontrollers;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.chatservice.entities.MessageEntity;
import com.app.chatservice.services.MessageService;
import com.app.chatservice.superentities.Message;

@RestController
@RequestMapping("message")
public class MessageRestController {
    @Autowired
    private MessageService service;

    Logger logger = Logger.getLogger(getClass().getName());

    @PostMapping("/create")
    public ResponseEntity create(MessageEntity entity, @RequestParam(required = false) MultipartFile[] files){
        logger.info(entity.toString());
        logger.info(((Integer)files.length).toString());

        if(files[0].getOriginalFilename().equals("")){
            files = null;
        }

        Message message = service.create(entity, files);
        return ResponseEntity.ok().body(message);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestParam String messageId){
        Object result = service.delete(messageId);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestParam String messageId, 
    @RequestParam String editedContent){
        logger.info(messageId);
        logger.info(editedContent);

        Object result = service.update(messageId, editedContent);

        return ResponseEntity.ok().body(result);
    }
}
