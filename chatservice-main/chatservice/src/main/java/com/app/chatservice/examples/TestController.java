package com.app.chatservice.examples;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.chatservice.entities.AppUserEntity;

@RestController
public class TestController {
  @Autowired
  private TestService service;

  @GetMapping("/rest")
  public List<AppUserEntity> getAppuserEntity(){
    UUID uuid = UUID.randomUUID();
    System.out.println(uuid.toString().length());
    
    return service.getAppuserEntity();
  }
}
