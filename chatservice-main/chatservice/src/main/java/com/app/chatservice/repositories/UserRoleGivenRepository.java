package com.app.chatservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.chatservice.entities.UserRoleGivenEntity;
import com.app.chatservice.entities.UserRoleGivenId;

public interface UserRoleGivenRepository extends JpaRepository<UserRoleGivenEntity, UserRoleGivenId> {

    Optional<UserRoleGivenEntity> findById(UserRoleGivenId userGivenRole);
}
