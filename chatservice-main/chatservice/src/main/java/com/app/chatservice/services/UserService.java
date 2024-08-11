package com.app.chatservice.services;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.chatservice.entities.AppUserEntity;
import com.app.chatservice.processors.LoginProcessor;
import com.app.chatservice.processors.LogoutProcessor;
import com.app.chatservice.repositories.AppuserRepository;
import com.app.chatservice.sessionscoped.SessionId;
import com.app.chatservice.util.DateFormat;
import com.app.chatservice.util.FilePathBuilder;
import com.app.chatservice.util.FileUploader;
import com.app.chatservice.util.ImageResize;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private AppuserRepository repository;

    @Autowired
    private LoginProcessor loginProcessor;

    @Autowired
    private LogoutProcessor logoutProcessor;

    @Autowired
    private SessionId sessionId;

    @Autowired
    private FileUploader uploader;

    @Autowired
    private HttpServletResponse response;

    @Value("${size.userpfp}")
    private int sizeUserPfp;


    private Logger logger = Logger.getLogger(getClass().getName());

    public String joinUser(AppUserEntity entity) {
        System.out.println(entity);
        String trimmedDisplayName = entity.getAppuserDisplayName().trim();
        if (trimmedDisplayName.length() == 0) {
            entity.setAppuserDisplayName(entity.getAppuserUkName());
        } // 디스플레이 이름 입력 하지 않았을시 고유이름 과 동일 하게

        String uuid = UUID.randomUUID().toString();
        entity.setAppuserId(uuid);

        try { // 가입 실패 시 회원 가입 페이지로 성공시 홈페이지로
            AppUserEntity result = repository.save(entity);
            loginProcessor.preprocessLogin(false, result.getAppuserId());
            return "/";
        } catch (Exception e) {
            e.printStackTrace();
            return "join";
        }
    }

    public boolean emailValCheck(String emailVal) {

        boolean exists = false;
        String email = repository.existsByAppuserEmail(emailVal);
        System.out.println(email);
        if (email != null) {
            exists = true;
        }
        return exists;
    }

    public Boolean ukNameCheck(String ukNameVal) {
        boolean exists = false;
        String ukName = repository.existsByAppuserUkName(ukNameVal);
        if (ukName != null) {
            exists = true;
        }
        return exists;
    }

    public AppUserEntity getLoginUser(String appuserEmail, String appuserPassword, String keepLoggedIn) {
        AppUserEntity result = repository.findByAppuserEmailAndAppuserPassword(appuserEmail, appuserPassword);
        sessionId.setLoginAttempted(true); // 로그인 시도가 있었다면 true로 변경

        if (result == null) { // 로그인 실패시 null 리턴
            logger.info("user do not exist");
            return null;
        } // 성공 했다면 세션 추가
        logger.info(result.toString());

        if(keepLoggedIn == null){ // 로그인 유지 안 함
            loginProcessor.preprocessLogin(false, result.getAppuserId());
        } else{ // 유지함
            loginProcessor.preprocessLogin(true, result.getAppuserId()); 
        }
        return result;
    }

    public void logout() {
        System.out.println("logout");
        sessionId.setSessionId(null);
        sessionId.setBeingLoggedOut(true);
        sessionId.setLoginAttempted(false); //로그아웃 시 시도 false로 다시 변경
    }

    public void find(String keyword) {
        logger.info(keyword);
        //
    }

    public String uploadFile(MultipartFile file) {
        String fileName = 
        uploader.uploadFileAndGetChangedFileName(file, "userpfp",350 ,350); 

        return fileName;
    }

    public AppUserEntity findById(String appuserId){
        AppUserEntity entity = repository.findByAppuserId(appuserId);
        return entity;
    }

    public AppUserEntity findByIdWithNoPassword(String appuserId) {
        AppUserEntity entity = repository.findByIdWithNoPassword(appuserId);
        return entity;
    }

    public AppUserEntity findByEmail(String keyword) {
        AppUserEntity entity = repository.findByAppuserEmail(keyword);
        return entity;
    }

    public AppUserEntity findByUkName(String keyword) {
        AppUserEntity entity = repository.findByAppuserUkName(keyword);
        return entity;
    }

    public List<AppUserEntity> findByDisplayName(String keyword) {
        List<AppUserEntity> entities = repository.findByAppuserDisplayName(keyword);
        return entities;
    }

    public List<AppUserEntity> listByList(String[] ids) { // 미완성 

        try {
            List<AppUserEntity> entities = repository.findAllById(Arrays.asList(ids));
            return entities;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public AppUserEntity updateUserPfp(String appuserId, MultipartFile file) {

        AppUserEntity entity = null;
        String fileNmae = uploader.uploadFileAndGetChangedFileName(file, "userpfp", sizeUserPfp, sizeUserPfp);
        try {
            repository.updateUserPfp(fileNmae, appuserId);
            entity = repository.findByAppuserId(appuserId);
            Thread.sleep(800);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entity;
    }

    public String findUserGenPfpById(String appuserId){
        AppUserEntity appUserEntity = repository.findById(appuserId).get();
        return appUserEntity.getAppuserGenPfp();
    }

    public List<String> findUserNamesByIds(List<String> ids) {

        List<String> userNames = repository.findUserNamesByIds(ids);
        return userNames;
    }

    public List<AppUserEntity> userListById(List<String> appuserIds){

        List<AppUserEntity> userList = repository.findAllById(appuserIds);
        return userList;
    }

    public String passwordConfirm(String userId, String password) {
        String result = null;
        try {
            String appuserId = repository.passwordConfirm(userId, password);
            logger.info(appuserId);

            if(appuserId == null){
                result = "not verified";
            }else{
                result = "verified";
            }
        } catch (Exception e) {
        }

        return result;
    }

    @Transactional
    public AppUserEntity updateDisplayName(String appuserId, String appuserDisplayName) {
        AppUserEntity appUserEntity = repository.findByAppuserId(appuserId);
        appUserEntity.setAppuserDisplayName(appuserDisplayName);

        return appUserEntity;
    }

    @Transactional
    public AppUserEntity updateAbout(String appuserId, String appuserAbout) {
        AppUserEntity appUserEntity = repository.findByAppuserId(appuserId);
        appUserEntity.setAppuserAbout(appuserAbout);

        return appUserEntity;
    }

    @Transactional
    public AppUserEntity updatePassword(String appuserId, String appuserPassword) {

        AppUserEntity appUserEntity = repository.findByAppuserId(appuserId);
        appUserEntity.setAppuserPassword(appuserPassword);

        return appUserEntity;
    }

    public void changeLoginAttemptToFalse() {
        sessionId.setLoginAttempted(false);
    }




}
