package com.app.chatservice.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.chatservice.entities.DisplayMessageEntity;
import com.app.chatservice.services.DisplayMessageService;

@RestController
@RequestMapping("displaymessage")
public class DisplayMessageRestController {

    @Autowired
    private DisplayMessageService service;

    @GetMapping("findall")
    public List<DisplayMessageEntity> findAll(){
        List<DisplayMessageEntity> entities = service.findAll();
        return entities;
    }

    @GetMapping("findlatest")
    public ResponseEntity findLatest(@RequestParam String channelId){

        Object result = service.findLatest(channelId);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("findbyid")
    public ResponseEntity findById(@RequestParam String messageId){
        Object result = service.findById(messageId);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("findolder")
    public ResponseEntity findOlder(@RequestParam String channelId, @RequestParam String oldestMessageId){
        Object result = service.findOlder(channelId, oldestMessageId);
        return ResponseEntity.ok().body(result);
    }
}
