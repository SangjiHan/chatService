package com.app.chatservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.chatservice.entities.MessageMediaEntity;
import com.app.chatservice.repositories.MessageMediaRepository;
import com.app.chatservice.util.FileUploader;


@Service
public class MessageMediaService {

    @Autowired
    private MessageMediaRepository repository;

    @Autowired
    private FileUploader fileUploader;

    private Logger logger = Logger.getLogger(getClass().getName());

    @Value("${size.messagemedia}")
    private int sizeMessageMedia;

    public Object create(String messageMediaMessage, MultipartFile[] files) {

        List<MessageMediaEntity> entities = new ArrayList<>();
        List<MediaMetaData> meidaMetaData = new ArrayList<>();

        for(MultipartFile file : files){ // 여러개 파일 업로드 하고 변경된 이름을 가져옴
            String fileName = fileUploader.uploadFileAndGetChangedFileName(file, "message", sizeMessageMedia, sizeMessageMedia);
            logger.info("fileName: " + fileName);

            MediaMetaData mediaMetaDatum = new MediaMetaData();
            mediaMetaDatum.setFileName(fileName);
            mediaMetaDatum.setFileType(file.getContentType());

            meidaMetaData.add(mediaMetaDatum);
        }

        try {
            int waitTime = 600 + 200 * files.length;
            Thread.sleep(waitTime);
        } catch (Exception e) {
            // TODO: handle exception
        }

        int count = 1;
        for(MediaMetaData mediaMetaDatum : meidaMetaData){ // 메세지 미디어 db에 추가
            MessageMediaEntity messageMediaEntity = new MessageMediaEntity();
            messageMediaEntity.setMessageMediaFileName(mediaMetaDatum.getFileName());
            messageMediaEntity.setMessageMediaType(mediaMetaDatum.getFileType());
            messageMediaEntity.setMessageMediaOrder(count++);
            messageMediaEntity.setMessageMediaMessage(messageMediaMessage);

            try {
                messageMediaEntity = repository.save(messageMediaEntity);
            } catch (Exception e) {
                if(e.getCause().toString().contains("ORA-02291")){
                    return "no message found";
                }
            }
            entities.add(messageMediaEntity);
        }
        return entities;
    }

    public List<MessageMediaEntity> findByMessage(String messageMediaMessage){
        List<MessageMediaEntity> entities = null;
        try {
           entities = repository.findByMessageMediaMessage(messageMediaMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entities;
    }

}

class MediaMetaData{

    private String fileName;
    private String fileType;

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    
}

