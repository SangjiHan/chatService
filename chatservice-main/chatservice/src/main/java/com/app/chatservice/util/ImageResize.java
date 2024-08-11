package com.app.chatservice.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import net.coobird.thumbnailator.Thumbnails;

public class ImageResize {
    public static byte[] resizeImage(byte[] originalImage, int width, int height) throws Exception{ // 이미지를 원하는 해상도로 바꾸고 바이트스트림 형태로 리턴


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Thumbnails.of(new ByteArrayInputStream(originalImage))
            .size(width, height).outputFormat("jpeg")
            .toOutputStream(outputStream);
        return outputStream.toByteArray();
    }
}
