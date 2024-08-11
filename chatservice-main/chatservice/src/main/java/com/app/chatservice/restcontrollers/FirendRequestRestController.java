package com.app.chatservice.restcontrollers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.chatservice.entities.AppUserEntity;
import com.app.chatservice.entities.FriendRequestEntity;
import com.app.chatservice.entities.FriendRequestId;
import com.app.chatservice.services.FriendRequestService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("friendreq")
public class FirendRequestRestController {
    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private FriendRequestService service;

    @PostMapping("add")
    public ResponseEntity<String> add(FriendRequestId friendRequestId){
        logger.info(friendRequestId.toString());
        ResponseEntity<String> result = service.add(friendRequestId);
        return result;
    }

    @GetMapping("findbyid")
    public ResponseEntity<FriendRequestEntity> findById(FriendRequestId friendRequestId){
        FriendRequestEntity entity = service.findById(friendRequestId);

        if(entity == null){
            return ResponseEntity.badRequest().body(entity);
        }
        return ResponseEntity.ok().body(entity);
    }

    @GetMapping("findbysender") // 유저가 친추한 유저 목록 가져옴
    public List<AppUserEntity> findBySender(@RequestParam String friendRequestSender){
        logger.info(friendRequestSender);
        List<AppUserEntity> result = service.findBySender(friendRequestSender);

        return result;
    }

    
    @GetMapping("findbyreceiver")  // 유저가 친추 받은 목록 가져옴
    public List<AppUserEntity> findByReceiver(@RequestParam String friendRequestReceiver){
        logger.info(friendRequestReceiver);
        List<AppUserEntity> result = service.findByReceiver(friendRequestReceiver);

        return result;
    }

    @DeleteMapping("delete")
    public String delete(FriendRequestId friendRequestId){
        String result = service.delete(friendRequestId);
        return result;
    }

}
