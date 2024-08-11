package com.app.chatservice.restcontrollers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.chatservice.entities.MessageMediaEntity;
import com.app.chatservice.services.MessageMediaService;

@RestController
@RequestMapping("messagemedia")
public class MessageMediaRestController {
    
    @Autowired
    private MessageMediaService service;

    private Logger logger = Logger.getLogger(getClass().getName());

    @PostMapping("create")
    public ResponseEntity create(String messageMediaMessage, @RequestParam(required = false) MultipartFile[] files){
        System.out.println(messageMediaMessage);

        for(MultipartFile f : files){
            System.out.println(f.getOriginalFilename());
        }
        Object result = service.create(messageMediaMessage, files);

        if(result instanceof String){
            ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok().body(result);
    }


}
