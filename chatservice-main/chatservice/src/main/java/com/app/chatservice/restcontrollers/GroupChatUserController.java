package com.app.chatservice.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.chatservice.services.GroupChatUserService;

@RestController
@RequestMapping("/groupchatuser")
public class GroupChatUserController {

    @Autowired 
    private GroupChatUserService service;

    @GetMapping("userlistbygroupid")
    public ResponseEntity userListByGroupId(@RequestParam String groupId){
        Object result = service.userListByGroupId(groupId);
        return ResponseEntity.ok().body(result);
    }
}
