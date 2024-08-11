package com.app.chatservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.app.chatservice.entities.AppUserEntity;
import com.app.chatservice.entities.FriendRequestEntity;
import com.app.chatservice.entities.FriendRequestId;
import com.app.chatservice.repositories.AppuserRepository;
import com.app.chatservice.repositories.FriendRequestRepository;


@Service
public class FriendRequestService {

    @Autowired
    private FriendRequestRepository repository;

    @Autowired
    private AppuserRepository appuserRepository;

    private Logger logger = Logger.getLogger(getClass().getName());

    public ResponseEntity<String> add(FriendRequestId friendRequestId) {

        if(friendRequestId.getFriendRequestReceiver().equals(friendRequestId.getFriendRequestSender())){
            return ResponseEntity.badRequest().body("You can not add yourself");
        }

        FriendRequestId friendRequestIdReversed = 
            new FriendRequestId(friendRequestId.getFriendRequestReceiver(), friendRequestId.getFriendRequestSender());

        try {
            boolean exists = repository.existsById(friendRequestId);
            if(exists){
                return ResponseEntity.badRequest().body("you already added");
            }

            boolean existsInReverse = repository.existsById(friendRequestIdReversed);

            if(existsInReverse){
                return ResponseEntity.badRequest().body("somone already added you");
            }

            FriendRequestEntity entity = new FriendRequestEntity();
            entity.setFriendRequestId(friendRequestId);
            entity = repository.save(entity);
            return ResponseEntity.ok().body("success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("failed");
        }

    }

    public FriendRequestEntity findById(FriendRequestId friendRequestId) {
        Optional<FriendRequestEntity> entity = null;
        try{
            entity = repository.findById(friendRequestId);
            return entity.get();
        }catch(Exception e){
            return null;
        }
    }

    public List<AppUserEntity> findBySender(String friendRequestSender) { // 보낸이를 조건으로 받은이의 리스트를 가져옴
        List<AppUserEntity> appUserEntities = null;
        List<FriendRequestEntity> friendRequestEntities = null;
        try {
            friendRequestEntities =
             repository.findByFriendRequestId_FriendRequestSender(friendRequestSender);

            List<String> requestReceivers = new ArrayList<>();

            friendRequestEntities.forEach((entity) -> {
                requestReceivers.add(entity.getFriendRequestId().getFriendRequestReceiver());
            });
            appUserEntities = appuserRepository.findAllById(requestReceivers);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return appUserEntities;
    }

    public List<AppUserEntity> findByReceiver(String friendRequestReceiver) {
        List<AppUserEntity> appUserEntities = null;
        List<FriendRequestEntity> entities = null;
        try {
            entities =
             repository.findByFriendRequestId_FriendRequestReceiver(friendRequestReceiver);

             List<String> requestSenders = new ArrayList<>();

             entities.forEach((entitiy) -> {
                requestSenders.add(entitiy.getFriendRequestId().getFriendRequestSender());
             });

             appUserEntities = appuserRepository.findAllById(requestSenders);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return appUserEntities;
    }

    public String delete(FriendRequestId friendRequestId) {
        FriendRequestId friendRequestIdReversed = new FriendRequestId(friendRequestId.getFriendRequestReceiver(), friendRequestId.getFriendRequestSender());

        logger.info(friendRequestId.toString());
        try {
            repository.deleteById(friendRequestId);
            repository.deleteById(friendRequestIdReversed);
            logger.info(((Long)repository.count()).toString());
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }

}
