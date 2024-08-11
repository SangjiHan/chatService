package com.app.chatservice.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.chatservice.entities.AppUserEntity;
import com.app.chatservice.entities.GroupChatUserEntity;
import com.app.chatservice.entities.GroupChatUserId;
import com.app.chatservice.repositories.GroupChatUserRepository;

@Service
public class GroupChatUserService {

    @Autowired
    private GroupChatUserRepository repository;

    @Autowired
    private UserService userService;

    private Logger logger = Logger.getLogger(getClass().getName());

    public GroupChatUserEntity create(String user, String groupChatId) {
        GroupChatUserId groupChatUserId = new GroupChatUserId(user, groupChatId);
        GroupChatUserEntity groupChatUserEntity = new GroupChatUserEntity();

        try {
            groupChatUserEntity.setGroupChatUserId(groupChatUserId);
            groupChatUserEntity = repository.save(groupChatUserEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return groupChatUserEntity;
    }

    public List<String> findUsersById(String groupChatId) {

        List<String> groupChatUsers = null;

        try {
            groupChatUsers = repository.findUsersById(groupChatId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return groupChatUsers;
    }

    public List<String> findGroupChatGroupsByGroupChatUser(String appuserId) {
        List<String> groupChatGroups = repository.findGroupChatGroupsByGroupChatUser(appuserId);

        return groupChatGroups;
    }

    public List<String> findAllGroupsById(String appuserId) {

        List<String> groupIds = null;

        try {
            groupIds = repository.findAllByUser(appuserId);

            for(String groupId : groupIds){
                logger.info(groupId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return groupIds;
    }

    public List<String> findGroupUsersExcludeSelf(String appuserId, String groupId) {

        List<String> groupUsersExcludeSelf = repository.findGroupUsersExcludeSelf(appuserId, groupId);

        return groupUsersExcludeSelf;
    }

    public Object userListByGroupId(String groupId) {

        List<String> groupUsers = repository.findUsersById(groupId);

        for(String groupUser : groupUsers){
            logger.info(groupUser);
        }

        List<AppUserEntity> appUserEntities = userService.userListById(groupUsers);

        return appUserEntities;
    }

}
