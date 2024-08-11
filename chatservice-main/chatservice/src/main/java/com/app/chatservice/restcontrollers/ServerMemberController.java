package com.app.chatservice.restcontrollers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.chatservice.entities.ServerEntity;
import com.app.chatservice.entities.ServerMemberEntity;
import com.app.chatservice.entities.ServerMemberId;
import com.app.chatservice.services.ServerMemberService;

@RestController
@RequestMapping("/servermember")
public class ServerMemberController {
    @Autowired
    private ServerMemberService service;

    private Logger logger = 
        Logger.getLogger(getClass().getName());

    @PostMapping("/create")
    public String addServerMember(ServerMemberId serverMemberId) {
        logger.info("ADD SERVER MEMBER");
        logger.info(serverMemberId.getServerMemberServer());
        logger.info(serverMemberId.getServerMemberUser());
        String result = service.addServerMember(serverMemberId);
        return result;
    }

    @GetMapping("/listbyuser") // 유저 아이디로 서버아이디 어레이 가져옴 (직접적으로 쓰이지 않음)
    public List<String> listByUser(@RequestParam String requestUser){
        List<String> list = service.listByUser(requestUser);
        return list;
    }

    @GetMapping("lastvisitedbyuserandserver")
    public ResponseEntity lastVisitedByUserAndServer(@RequestParam String serverId, @RequestParam String appuserId){
        String result = service.lastVisitedByUserAndServer(serverId, appuserId);

        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/updatelastvisited")
    public ResponseEntity updateLastVisited(@RequestParam String serverId, @RequestParam String appuserId, @RequestParam String channelId){
        logger.info("serverId: " + serverId);
        logger.info("appuserId: " + appuserId);
        logger.info("channelId: " + channelId);

        ServerMemberEntity result = service.updateLastVisited(serverId, appuserId, channelId);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/listbyserver")
    public ResponseEntity listByServer(@RequestParam String serverId){

        List<ServerMemberEntity> result = service.listByServer(serverId);

        return ResponseEntity.ok().body(result);
    }

}
