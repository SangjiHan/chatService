package com.app.chatservice.services;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.chatservice.entities.ReactionLogEntity;
import com.app.chatservice.entities.ReactionLogId;
import com.app.chatservice.repositories.ReactionLogRepository;

@Service
public class ReactionLogService {

    @Autowired
    private ReactionLogRepository repository;

    private Logger logger = Logger.getLogger(getClass().getName());

    public Object create(ReactionLogId id) {
        ReactionLogEntity result = null;
        logger.info(id.toString());
        try {
            ReactionLogEntity reactionLogEntity = new ReactionLogEntity();
            reactionLogEntity.setReactionLogId(id);
            result = repository.save(reactionLogEntity);
        } catch (Exception e) {
        }

        return result;
    }

}
