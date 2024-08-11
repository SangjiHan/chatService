package com.app.chatservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.chatservice.entities.FriendEntity;
import com.app.chatservice.entities.FriendId;
import com.app.chatservice.entities.FriendRequestId;
import com.app.chatservice.repositories.FriendRepository;
import com.app.chatservice.repositories.FriendRequestRepository;

@Service
public class FriendService {

    @Autowired
    private FriendRepository repository;

    @Autowired
    private FriendRequestService friendRequestService;

    public String accept(FriendId friendId) {
        FriendId friendIdReversed = new FriendId(friendId.getFriend2(), friendId.getFriend1());

        FriendEntity entity = new FriendEntity();
        entity.setFriend(friendId);

        FriendEntity entityReservsed = new FriendEntity();
        entityReservsed.setFriend(friendIdReversed);
        try {
            entity = repository.save(entity);
            entityReservsed = repository.save(entityReservsed);

            FriendRequestId friendRequestId = new FriendRequestId(friendId.getFriend1(), friendId.getFriend2());
            friendRequestService.delete(friendRequestId);
            return "success";
        } catch (Exception e) {
            return "failed";
        }

    }

    public List<FriendEntity> list(String requestUser) {

        try {
            List<FriendEntity> list = repository.findByFriend_Friend1(requestUser);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
