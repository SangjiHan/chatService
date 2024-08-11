package com.app.chatservice.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploader {

    @Autowired
    private FilePathBuilder builder;

    private Logger logger = Logger.getLogger(getClass().getName());

    public void uploadFile(String dest, byte[] byteData) throws IOException {
        Path path = Paths.get(dest);
        Path parent = path.getParent();
        if(!Files.exists(parent)){ // 폴더가 없을 시 생성
            logger.info("does not exist");
            Files.createDirectories(parent);
        }   
        
        logger.info("path: " + path);
        Files.write(path, byteData); // 경로로 업로드
    }

    public String uploadFileAndGetChangedFileName( // 파일을 업로드하고 파일이름을 리턴 js fetch를 위해서 사용됨
        MultipartFile file, String folderName, int height, int width){
        String fileName = DateFormat.mergeFileNameAndDate(file.getOriginalFilename());
        String finalDir = builder.builFilePath(fileName, folderName);

        if(file.getContentType() != null && file.getContentType().contains("video")){ // 영상일 때

            try {
                uploadFile(finalDir, file.getBytes());
                return fileName; 
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        try {
            byte[] resizedImage = 
                ImageResize.resizeImage(file.getBytes(), width, height);
            logger.info("image resizing");
            uploadFile(finalDir, resizedImage);
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
