package com.app.chatservice.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.ssl.SslProperties.Bundles.Watch.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.chatservice.entities.ServerEntity;
import com.app.chatservice.entities.ServerMemberEntity;
import com.app.chatservice.entities.ServerMemberId;
import com.app.chatservice.repositories.ServerMemberRepository;
import com.app.chatservice.repositories.ServerRepository;
import com.app.chatservice.util.DateFormat;
import com.app.chatservice.util.FilePathBuilder;
import com.app.chatservice.util.FileUploader;
import com.app.chatservice.util.ImageResize;

@Service
public class ServerService {
    @Autowired
    private ServerRepository repository;

    @Autowired
    private ServerMemberRepository serverMemberRepository;

    @Autowired
    private ServerMemberService serverMemberService;

    @Autowired
    private FileUploader uploader;

    private Logger logger = 
        Logger.getLogger(getClass().getName());

    @Value("${media.filepath}")
    private String filePath;

    @Value("${size.serverpfp}")
    private int pfpSize;

    public ServerMemberId createServer(ServerEntity entity) {
        ServerMemberId memberId = null;
        try {
            String uuid = UUID.randomUUID().toString();
            entity.setServerId(uuid); // uuid 설정
            logger.info(entity.toString());
            ServerEntity result = repository.save(entity); // 리포저터리에 저장
            memberId = new ServerMemberId(result.getServerOwner(), result.getServerId()); // 리턴할 멤버 아이디

            Thread.sleep(800);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return memberId;
    }

    public String fileUpload(MultipartFile file){
        if(file.isEmpty()){
            return null;
        }

        String fileName =
         uploader.uploadFileAndGetChangedFileName(file, "serverpfp", pfpSize , pfpSize);
        
        return fileName;
    }

    public List<ServerEntity> listByIds(String[] requestIds) { 
        List<ServerEntity> entities = repository.findAllById(Arrays.asList(requestIds));

        return entities;
    }

    public List<ServerEntity> listByUser(String user) {
        List<ServerEntity> entities = null;

        try {
            List<String> serverIdList = serverMemberRepository.findByServerMemberUserIn(user);
            entities = repository.findAllById(serverIdList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entities;
    }

    public ServerEntity byId(String serverId){
        ServerEntity entity = null;
        try {
           entity = repository.findByServerId(serverId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entity;
    }

    public ServerEntity findByServerIdAndServerOwner(String serverId, String serverOwner){
        ServerEntity entity = null;
        try {
            entity = repository.findByServerIdAndServerOwner(serverId, serverOwner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    public List<ServerEntity> findAllExcludeJoined(String appuserId) {

        List<ServerEntity> serverEntities = null;

        try {
            serverEntities = repository.findAllExcludeJoined(appuserId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return serverEntities;
    };

}
