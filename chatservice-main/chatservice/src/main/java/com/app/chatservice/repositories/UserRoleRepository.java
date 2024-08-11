package com.app.chatservice.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.chatservice.entities.UserRoleEntity;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, String>{
    
    UserRoleEntity findByUserRoleId(String userRoleId);
    List<UserRoleEntity> findByUserRoleName(String userRoleName);
    List<UserRoleEntity> findByUserRoleServer(String userRoleServer);
}
