package com.app.chatservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Date;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.chatservice.entities.AppUserEntity;

import jakarta.transaction.Transactional;

public interface AppuserRepository extends JpaRepository<AppUserEntity, String> {

    
    // boolean existsByAppuserUkName(String ukNameVal);
    @Query(value = "select appuser_uk_name from appuser where appuser_uk_name= ?1", 
        nativeQuery = true)
    String existsByAppuserUkName(String ukNameVal);

    // boolean existsByAppuserEmail(String emailVal);
    @Query(value = "select appuser_id from appuser where appuser_email= ?1",
        nativeQuery = true)
    String existsByAppuserEmail(String emailVal);

    // UserId로 회원 찾기 (Primary Key)
    AppUserEntity findByAppuserId(String appuserId);
    // UkName으로 회원 찾기 (Unique Key)
    AppUserEntity findByAppuserUkName(String appuserUkName);
    // Email로 회원 찾기 (Unique Key)
    AppUserEntity findByAppuserEmail(String appuserEmail);
    // DisplayName으로 회원 모두 찾기
    List<AppUserEntity> findByAppuserDisplayName(String appuserDisplayName);
    // Password로 회원 모두 찾기
    List<AppUserEntity> findByAppuserPassword(String appuserPassword);
    // RegDate로 회원 모두 찾기
    List<AppUserEntity> findByAppuserRegDate(Date appuserRegDate);
    // GenPfp로 회원 모두 찾기
    List<AppUserEntity> findByAppuserGenPfp(String appuserGenPfp);
    // About으로 회원 모두 찾기
    List<AppUserEntity> findByAppuserAbout(String appuserAbout);

    // Email과 Password로 회원 찾기
    AppUserEntity findByAppuserEmailAndAppuserPassword(String appuserEmail, String appuserPassword);

    // UkName, DisplayName, Email 부분일치 찾기
    @Query("SELECT u FROM AppUserEntity u WHERE u.appuserUkName LIKE %:keyword% OR u.appuserDisplayName LIKE %:keyword% OR u.appuserEmail LIKE %:keyword%")
    List<AppUserEntity> findByUserKeyword(@Param("keyword") String keyword);

    @Query(value = "UPDATE APPUSER SET APPUSER_GEN_PFP = ?1 WHERE APPUSER_ID = ?2", 
    nativeQuery = true)
    @Modifying
    @Transactional
    void updateUserPfp(String userGenPfp, String userid);

    @Query(value = "SELECT APPUSER_ID, APPUSER_EMAIL, APPUSER_DISPLAY_NAME, APPUSER_UK_NAME, " +
    "APPUSER_REG_DATE, APPUSER_GEN_PFP, APPUSER_ABOUT FROM APPUSER WHERE APPUSER_ID = ?1" , nativeQuery = true)
    AppUserEntity findByIdWithNoPassword(String appuserId);

    @Query(value = "SELECT APPUSER_DISPLAY_NAME FROM APPUSER WHERE APPUSER_ID IN ?1", nativeQuery = true)
    List<String> findUserNamesByIds(List<String> ids);

    @Query(value = "SELECT APPUSER_ID FROM APPUSER WHERE APPUSER_ID = ?1 AND APPUSER_PASSWORD = ?2",
    nativeQuery = true)
    String passwordConfirm(String userId, String password);
} 