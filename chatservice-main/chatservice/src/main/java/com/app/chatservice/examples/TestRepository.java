package com.app.chatservice.examples;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.chatservice.entities.AppUserEntity;

@Repository
public interface TestRepository extends JpaRepository<AppUserEntity, String>{

}
