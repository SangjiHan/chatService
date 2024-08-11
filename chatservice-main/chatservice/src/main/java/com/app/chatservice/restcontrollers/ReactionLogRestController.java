package com.app.chatservice.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.chatservice.entities.ReactionLogEntity;
import com.app.chatservice.entities.ReactionLogId;
import com.app.chatservice.services.ReactionLogService;

@RestController
@RequestMapping("reactionlog")
public class ReactionLogRestController {

    @Autowired
    private ReactionLogService service;

    @PostMapping("create")
    public ResponseEntity create(ReactionLogId id){
        Object result = service.create(id);

        return ResponseEntity.ok().body(result);
    }
}
