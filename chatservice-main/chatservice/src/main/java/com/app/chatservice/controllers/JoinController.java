package com.app.chatservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.app.chatservice.entities.AppUserEntity;
import com.app.chatservice.processors.LoginProcessor;
import com.app.chatservice.sessionscoped.SessionId;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/join")
public class JoinController {

  @GetMapping
  public String join(){
    System.out.println("join");
    return "join";
  }
  
}
