package com.app.chatservice.restcontrollers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.chatservice.entities.GroupChatEntity;
import com.app.chatservice.services.GroupChatService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/groupchat")
public class GroupChatController {

    @Autowired
    private GroupChatService service;

    private Logger logger = Logger.getLogger(getClass().getName());

    @PostMapping("/create")
    public ResponseEntity create(@RequestParam String inviter, @RequestParam String[] invitees){
        logger.info("INVITER: " + inviter);
        Object result = service.create(inviter, invitees);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/listbyuser") // 유저가 포함된 그륩쳇들을 가져온다
    public ResponseEntity listByUserId(@RequestParam String appuserId){
        Object result = service.listByUserId(appuserId);
        return ResponseEntity.ok().body(result);
    }

}
