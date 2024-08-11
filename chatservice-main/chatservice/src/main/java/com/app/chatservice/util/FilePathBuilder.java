package com.app.chatservice.util;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class FilePathBuilder {
    @Value("${media.filepath}")
    private String filePath;

    private Logger logger = Logger.getLogger(getClass().getName());

    public String builFilePath(String fileName, String folder){ // 파일명과 폴더 이름을 변수로 받고 최종 경로를 생성함
        String userDir = System.getProperty("user.dir");
        logger.info("userDir: " + userDir);
        logger.info("fileName: " + fileName);
        logger.info("folder: " + folder);
        logger.info("filePath: " + filePath);
        String finalDir = userDir + "\\" + filePath + "\\" + folder
        + "\\" + fileName;

        logger.info("finalDir: " + finalDir);
        return finalDir;
    }
}
