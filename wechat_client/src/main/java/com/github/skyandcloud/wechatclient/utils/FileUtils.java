package com.github.skyandcloud.wechatclient.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description 文件工具类
 * @Author zheng.jiang
 * @Date 2024/11/24 16:18
 */
@Slf4j
public class FileUtils {

    /**
     * 创建临时文件
     *
     * @param fileName
     * @return
     */
    public static File createTempFile(String fileName, String suffix) throws IOException {
        try (InputStream inputStream = new ClassPathResource(fileName + suffix).getInputStream()) {
            File tempFile = File.createTempFile(fileName, suffix);
            try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                return tempFile;
            }
        } catch (IOException e) {
            log.error("文件复制失败");
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 删除文件
     *
     * @param file
     */
    public static Boolean delete(File file) {
        if (ObjectUtils.isEmpty(file) || !file.exists()) {
            return true;
        }

        return file.delete();
    }
}
