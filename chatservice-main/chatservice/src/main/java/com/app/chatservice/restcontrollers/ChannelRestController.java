package com.app.chatservice.restcontrollers;

import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.chatservice.entities.ChannelEntity;
import com.app.chatservice.services.ChannelService;

import io.micrometer.core.ipc.http.HttpSender.Response;

@RestController
@RequestMapping("channel")
public class ChannelRestController {
    Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private ChannelService service;

    @PostMapping("create")
    public ResponseEntity create(ChannelEntity entity){
        logger.info(entity.toString());

        Object result = service.create(entity);

        if(result instanceof String){
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("listbyserver")
    public List<ChannelEntity> listByServer(@RequestParam String serverId){
        List<ChannelEntity> result = service.listByServer(serverId);
        return result;
    }
}
