package com.app.chatservice.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;


public class DateFormat {
    private static Logger logger = Logger.getLogger(DateFormat.class.getName());


    public static String generateCurrentTimeStamp(){ // 현재 날짜 포맷을 리턴 (연도-월-일_시(24시)-분-초)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String timeStamp = dateFormat.format(new Date());
        logger.info(timeStamp.toString()); 
        return timeStamp;
    }

    public static String mergeFileNameAndDate(String fileName) { // 파일 명을 변수로 받고 현재 날짜와 합침 (날짜-파일명)
        StringBuilder sb = new StringBuilder();
        sb.append(generateCurrentTimeStamp());
        sb.append("_");
        sb.append(fileName);
        return sb.toString();
    }
}
