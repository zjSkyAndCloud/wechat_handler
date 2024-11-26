package com.github.skyandcloud.common.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description 字节流工具类
 * @Author zheng.jiang
 * @Date 2024/11/22 16:07
 */
public class ByteArrayStreamUtils {

    public static byte[] getBytes(InputStream inputStream) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); BufferedInputStream bis = new BufferedInputStream(inputStream)) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("serialize fail");
        }
    }
}
