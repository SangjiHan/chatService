package com.app.chatservice.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.chatservice.entities.AppUserEntity;
import com.app.chatservice.entities.ServerEntity;
import com.app.chatservice.entities.ServerMemberEntity;
import com.app.chatservice.entities.ServerMemberId;
import com.app.chatservice.repositories.AppuserRepository;
import com.app.chatservice.repositories.ServerMemberRepository;

@Service
public class ServerMemberService {
    @Autowired
    private ServerMemberRepository repository;

    @Autowired 
    private AppuserRepository appuserRepository;

    private Logger logger = 
        Logger.getLogger(getClass().getName());

    public String addServerMember(ServerMemberId memberId) {

        try {
            if(!repository.findById(memberId).isEmpty()){
                return "already exists";
            }

            AppUserEntity appUserEntity = appuserRepository.findByAppuserId(memberId.getServerMemberUser());

            ServerMemberEntity entity = new ServerMemberEntity();

            entity.setMemberId(memberId);
            entity.setServerMemberRegDate(new Date());
            entity.setServerMemberPfp(appUserEntity.getAppuserGenPfp());
            entity.setServerMemberUserName(appUserEntity.getAppuserDisplayName());

            ServerMemberEntity result = repository.save(entity);
            logger.info(result.toString());
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }

    }

    public List<String> listByUser(String requestUser) {// 미완성
        List<String> stringList = null;

        try {
            stringList = repository.findByServerMemberUserIn(requestUser);
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return stringList;
    }

    public ServerMemberEntity findByServerMemberId(ServerMemberId serverMemberId) {

        ServerMemberEntity entity = null;

        try {
            Optional<ServerMemberEntity> opt = repository.findById(serverMemberId);
            entity = opt.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entity;
    }

    public ServerMemberEntity updateLastVisited(String serverId, String appuserId, String channelId) {
        ServerMemberEntity entity = null;

        repository.updateLastVisited(serverId, appuserId, channelId);

        try {
            entity = repository.findById(new ServerMemberId(appuserId, serverId)).get();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    public String lastVisitedByUserAndServer(String serverId, String appuserId) {

        String lastVisited = null;
        try {
            lastVisited = repository.findLastVisitedByUserAndServer(serverId, appuserId);
            logger.info(lastVisited);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lastVisited;
    }

    public List<ServerMemberEntity> listByServer(String serverId) {

        List<ServerMemberEntity> serverMemberEntities = null;

        try {
            serverMemberEntities = repository.findAllByServer(serverId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return serverMemberEntities;
    }

    
}
