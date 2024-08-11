package com.app.chatservice.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.chatservice.services.UserService;

@RestController
@RequestMapping("/joincheck")
public class JoinCheckController {
    @Autowired
    private UserService service;

    @PostMapping("/email")
    public String emailValCheck(@RequestBody String emailVal){
        System.out.println(emailVal);
        Boolean exists = service.emailValCheck(emailVal);
        System.out.println(exists);
        return exists.toString();
    }

    @PostMapping("/ukname")
    public String ukNameCheck(@RequestBody String ukName){
        System.out.println(ukName);
        Boolean exists = service.ukNameCheck(ukName);
        System.out.println(exists);
        return exists.toString();
    }
}
