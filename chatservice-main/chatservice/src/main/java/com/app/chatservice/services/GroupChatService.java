package com.app.chatservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.chatservice.entities.AppUserEntity;
import com.app.chatservice.entities.GroupChatEntity;
import com.app.chatservice.entities.GroupChatUserEntity;
import com.app.chatservice.repositories.GroupChatRepository;
import com.app.chatservice.superentities.GroupChat;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class GroupChatService {

    @Autowired
    private GroupChatRepository repository;

    @Autowired
    private GroupChatUserService groupChatUserService;

    @Autowired
    private UserService userService;

    @PersistenceContext
    private EntityManager entityManager;

    private Logger logger = Logger.getLogger(getClass().getName());

    public Object create(String inviter, String[] invitees) {
        GroupChat groupChat = new GroupChat();

        GroupChatEntity groupChatEntity = new GroupChatEntity();
        groupChatEntity.setGroupChatCreator(inviter);

        logger.info("INVITER: " + inviter);

        List<AppUserEntity> groupChatUsers = new ArrayList<>();

        try {
            groupChatEntity = repository.save(groupChatEntity); // 그룹챗을 만들고
            AppUserEntity groupChatCreator = userService.findById(groupChatEntity.getGroupChatCreator());

            logger.info(groupChatEntity.toString());

            groupChat.setGroupCreator(groupChatCreator); 

            String groupChatId = groupChatEntity.getGroupChatId(); // 만들어진 그룹챗 아이디를 가져온다
            groupChatUserService.create(groupChatEntity.getGroupChatCreator(), groupChatId);

            for(String invitee : invitees){ // 초대된 인원들을 그룹쳇 유저에 넣는다
                logger.info("INVITEE: " + invitee);

                GroupChatUserEntity groupChatUserEntity = groupChatUserService.create(invitee, groupChatId); 
                AppUserEntity groupChatUser = userService.findById(groupChatUserEntity.getGroupChatUserId().getGroupChatUser());
                groupChatUsers.add(groupChatUser);
            }

            groupChat.setGroupChatUsers(groupChatUsers);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return groupChat;
    }

    public Object listByUserId(String appuserId) {

        List<GroupList> groupLists = new ArrayList();

        List<String> groupIds = groupChatUserService.findAllGroupsById(appuserId);

        for(String groupId : groupIds){
            GroupList groupList = new GroupList();
            groupList.setGroupChatId(groupId);

            List<String> groupUsersExcludeSelf = groupChatUserService.findGroupUsersExcludeSelf(appuserId, groupId);
            List<String> userNames = userService.findUserNamesByIds(groupUsersExcludeSelf);

            GroupChatEntity groupChatEntity = repository.findByGroupChatId(groupId);

            if(groupChatEntity.getGroupChatName() == null){
                String newName = getNewGroupChatName(userNames);
                groupList.setGroupChatName(newName);
            }else{
                groupList.setGroupChatName(groupChatEntity.getGroupChatName());
            }

            groupLists.add(groupList);
        }
        return groupLists;
    }

    private String getNewGroupChatName(List<String> userNames) {

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < userNames.size(); i++){
            if(i == 0){
                sb.append(userNames.get(i));
            }else{
                sb.append(", ");
                sb.append(userNames.get(i));
            }
        }
        return sb.toString();
    }
    
}

class GroupList{

    private String groupChatId;
    private String groupChatName;

    public String getGroupChatId() {
        return groupChatId;
    }
    public void setGroupChatId(String groupChatId) {
        this.groupChatId = groupChatId;
    }
    public String getGroupChatName() {
        return groupChatName;
    }
    public void setGroupChatName(String groupChatName) {
        this.groupChatName = groupChatName;
    }

    
}
