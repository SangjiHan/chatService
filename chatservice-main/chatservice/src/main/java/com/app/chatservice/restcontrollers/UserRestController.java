package com.app.chatservice.restcontrollers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.chatservice.entities.AppUserEntity;
import com.app.chatservice.repositories.AppuserRepository;
import com.app.chatservice.services.UserService;
import com.app.chatservice.sessionscoped.SessionId;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    private Logger logger = 
        Logger.getLogger(getClass().getName());

    @Autowired
    private SessionId sessionId;

    @GetMapping("/changetofalse")
    public boolean changeLoginAtteptToFalse(){
        userService.changeLoginAttemptToFalse();
        return sessionId.isLoginAttempted();
    }

    @GetMapping("/find") // 이메일, 고유이름 등의 키워드로 유저를 찾아낸다
    public ResponseEntity<AppUserEntity> find(@RequestParam String keyword){
        logger.info(keyword);
        userService.find(keyword);
        return null;
    }


    @GetMapping("/findbyemail") // 이메일로 유저를 찾아낸다
    public ResponseEntity<AppUserEntity> findByEmail(@RequestParam String keyword){
        logger.info(keyword);
        AppUserEntity entity = userService.findByEmail(keyword);
        return ResponseEntity.ok().body(entity);
    }

    @GetMapping("/findbyukname") // 고유이름으로 유저를 찾아낸다
    public ResponseEntity<AppUserEntity> findByUkName(@RequestParam String keyword){
        logger.info(keyword);
        AppUserEntity entity = userService.findByUkName(keyword);
        return ResponseEntity.ok().body(entity);
    }

    @GetMapping("/findbydisplayname") // 디스플레이 이름으로 유저를 찾아낸다
    public ResponseEntity<List<AppUserEntity>> findByDisplayName(@RequestParam String keyword){
        logger.info(keyword);
        List<AppUserEntity> entities = userService.findByDisplayName(keyword);
        return ResponseEntity.ok().body(entities);
    }

    @PostMapping("/userpfp") // 사용자 프사 바꿀시 실행
    public String changeUserPfp(@RequestParam MultipartFile file){
        String result = userService.uploadFile(file);
        return result;
    }

    @GetMapping("/listbylist") // 여러개의 아이디를 조건으로 여러개의 사용자 정보를 가져옴
    public ResponseEntity<List<AppUserEntity>> listByList(@RequestParam String[] ids){

        for(String id : ids){
            logger.info(id);
        }

        List<AppUserEntity> appUserEntities = userService.listByList(ids);

        return ResponseEntity.ok().body(appUserEntities);
    }

    @PutMapping("updatepfp")
    public ResponseEntity updateUserPfp(@RequestParam String appuserId, @RequestParam MultipartFile file){
        AppUserEntity entity =  userService.updateUserPfp(appuserId, file);

        return ResponseEntity.ok().body(entity);
    }

    @PostMapping("passwordcheck") // 비밀번호 확인
    public ResponseEntity passwordCheck(@RequestParam String appuserId, @RequestParam String appuserPassword){
        String result = userService.passwordConfirm(appuserId, appuserPassword);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("updatedisplayname") // 디스플레이 네임 변경
    public ResponseEntity updateDisplayName(@RequestParam String appuserId, 
    @RequestParam String appuserDisplayName){
        AppUserEntity result = userService.updateDisplayName(appuserId, appuserDisplayName);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("updatepassword") // 유저 비밀번호 변경
    public ResponseEntity updatePassword(@RequestParam String appuserId, 
    @RequestParam String appuserPassword){
        AppUserEntity result = userService.updatePassword(appuserId, appuserPassword);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("updateabout") // 유저 소개 변경
    public ResponseEntity updateAbout(@RequestParam String appuserId, 
    @RequestParam String appuserAbout){
        AppUserEntity result = userService.updateAbout(appuserId, appuserAbout);
        return ResponseEntity.ok().body(result);
    }


}
