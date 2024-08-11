package com.app.chatservice.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.chatservice.services.GroupMessageService;
import com.app.chatservice.superentities.DisplayMessage;

@RestController
@RequestMapping("/groupmessage")
public class GroupMessageController {
   
    @Autowired
    private GroupMessageService service;

    @GetMapping("findlatest")
    public ResponseEntity findLatest(@RequestParam String groupId){
        
        List<DisplayMessage> result = service.findLatest(groupId);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("findolder")
    public ResponseEntity findOlder(@RequestParam String groupId, 
    @RequestParam String oldestMessageId){
        List<DisplayMessage> result = service.findOlder(groupId, oldestMessageId);
        return ResponseEntity.ok().body(result);
    }
}
