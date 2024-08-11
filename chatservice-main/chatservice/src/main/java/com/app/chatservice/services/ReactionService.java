package com.app.chatservice.services;

import java.sql.SQLException;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.chatservice.entities.ReactionEntity;
import com.app.chatservice.repositories.ReactionRepository;
import com.app.chatservice.util.FileUploader;

import jakarta.persistence.PersistenceException;

@Service
public class ReactionService {

    @Autowired
    private ReactionRepository repository;

    @Autowired
    private FileUploader fileUploader;

    @Value("${size.reaction}")
    private int sizeReaction;

    private Logger logger = Logger.getLogger(getClass().getName());

    public Object create(MultipartFile file, ReactionEntity entity) {
        ReactionEntity result = null;
        String reactionFile = null;

        if(file != null){
            reactionFile = fileUploader.uploadFileAndGetChangedFileName(file, "reaction", sizeReaction, sizeReaction);
            entity.setReactionFile(reactionFile);
        }

        try {
            result = repository.save(entity);
        } catch (Exception e) {
            logger.info(e.getCause().toString());

            if(e.getCause().toString().contains("ORA-00001")){
                return "name already taken";
            }

            if(e.getCause().toString().contains("ORA-02291")){
                return "server does not exist";
            }
        }

        return result;
    }

    
}
