package com.app.chatservice.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.chatservice.entities.FriendEntity;
import com.app.chatservice.entities.FriendId;
import com.app.chatservice.entities.FriendRequestId;
import com.app.chatservice.services.FriendService;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("friend")
public class FriendRestController {

    @Autowired
    private FriendService service;

    @PostMapping("accept")
    public String accept(FriendId friendId){
        String result = service.accept(friendId);
        return result;
    }

    @GetMapping("list")
    public List<FriendEntity> list(@RequestParam String requestUser){
        List<FriendEntity> result = service.list(requestUser);
        return result;
    }
}
