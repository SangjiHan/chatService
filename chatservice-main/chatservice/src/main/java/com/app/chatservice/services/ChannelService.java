package com.app.chatservice.services;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.chatservice.entities.ChannelEntity;
import com.app.chatservice.repositories.ChannelRepository;


@Service
public class ChannelService {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private ChannelRepository repository;

    public Object create(ChannelEntity entity) {
        Object result = null;
        try {
            result = repository.save(entity);
            logger.info(result.toString());
        } catch (Exception e) {
            if(e.getCause().toString().contains("ORA-00001")){
                result = "name already taken";
            }
        }

        return result;
    }

    public List<ChannelEntity> listByServer(String serverId) {

        try {
            List<ChannelEntity> entities = repository.findByChannelServer(serverId);
            return entities;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String featuredChannel(String serverId) {
        /*String featuredChannel = repository.featuredChannel(serverId);
        logger.info(featuredChannel);
        return featuredChannel; */
        return null;
    }

    public boolean channelExist(String channelId) {

        boolean exists = repository.existsById(channelId);

        return exists;
    }

    public String findDefaultChannel(String serverId) {

        String defaultChannel = repository.findDefaultChannel(serverId);
        return defaultChannel;
    }

    public ChannelEntity findById(String channelId) {

        ChannelEntity entity = repository.findByChannelId(channelId);

        return entity;
    }

}
