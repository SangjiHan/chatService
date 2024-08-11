package com.app.chatservice.examples;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.chatservice.entities.AppUserEntity;

@Service
public class TestService {
  @Autowired
  private TestRepository repository;

  public List<AppUserEntity> getAppuserEntity(){
    
    return repository.findAll();
  }
}
