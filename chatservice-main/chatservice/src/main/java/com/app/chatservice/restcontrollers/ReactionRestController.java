package com.app.chatservice.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.chatservice.entities.ReactionEntity;
import com.app.chatservice.services.ReactionService;

@RestController
@RequestMapping("/reaction")
public class ReactionRestController {

    @Autowired
    private ReactionService service;

    @PostMapping("/create")
    public ResponseEntity create(MultipartFile file, ReactionEntity entity){
        Object result = service.create(file, entity);

        if(result instanceof String){
            return ResponseEntity.internalServerError().body(result);
        }
        return ResponseEntity.ok().body(result);
    }

}
