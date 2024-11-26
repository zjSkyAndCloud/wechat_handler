package com.github.skyandcloud.wechatclient.server.wechat;

import com.github.skyandcloud.common.utils.SleepUtils;
import com.github.skyandcloud.wechatclient.config.GlobalStaticConfig;
import com.github.skyandcloud.wechatclient.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description 微信文件服务
 * @Author zheng.jiang
 * @Date 2024/11/22 16:37
 */
@Slf4j
public class WechatFileServer {


    /**
     * 每次线程睡眠时间
     */
    private static final Long THREAD_SLEEP_TIME = 500L;

    /**
     * 最大等待一分钟
     */
    private static final Long MAX_WAIT_TIME = 60 * 1000L;

    /**
     * 文件存在目录
     */
    private static final String FILE_STRONG_PATH = "FileStorage\\File";


    /**
     * 根据文件名字返回文件流
     * 1.首先获取到文件位置
     * 2.等待文件下载 如果超过最大下载时间 则不再等待 并且删除
     * 3.把文件转换成文件流
     * 4.删除文件（同名文件可能出现问题 需删除）
     *
     * @param fileName   文件名
     * @param fileLength 文件大小
     * @return 文件流
     */
    public InputStream getWechatFileInputStream(String fileName, Long fileLength) {
        if (ObjectUtils.isEmpty(fileName)) {
            log.error("file name is empty");
            return null;
        }

        //获取文件目录前先等待一秒文件创建
        SleepUtils.sleep(THREAD_SLEEP_TIME);
        File file = getFile(fileName);
        if (ObjectUtils.isEmpty(file)) {
            log.error("file is not exist");
            return null;
        }

        Boolean download = waitDownload(file, fileLength);
        if (!download) {
            log.error("download failed");
            return null;
        }

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
            FileUtils.delete(file);
            return null;
        }

        FileUtils.delete(file);
        return inputStream;
    }

    /**
     * 获取到当月文件夹目录
     *
     * @return 当月文件夹目录
     */
    private String getSameMonthFolderDirectory() {
        String currentFileDatePath = getCurrentFileDatePath();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        return currentFileDatePath + File.separator + simpleDateFormat.format(new Date());
    }

    /**
     * 获取到上月文件夹目录
     *
     * @return 上月文件夹目录
     */
    private String getLastMonthFolderDirectory() {
        String currentFileDatePath = getCurrentFileDatePath();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        return currentFileDatePath + File.separator + simpleDateFormat.format(calendar.getTime());
    }

    /**
     * 获取到当前微信数据文件目录
     *
     * @return
     */
    private String getCurrentFileDatePath() {
        String currentDatePath = GlobalStaticConfig.getCurrentDataPath();
        if (ObjectUtils.isEmpty(currentDatePath)) {
            return null;
        }

        return currentDatePath + File.separator + FILE_STRONG_PATH;
    }

    /**
     * 获取到文件
     *
     * @param fileName 文件名
     * @return 返回文件
     */
    private File getFile(String fileName) {
        if (ObjectUtils.isEmpty(fileName)) {
            return null;
        }

        String sameMonthFolderDirectory = getSameMonthFolderDirectory();
        if (ObjectUtils.isEmpty(sameMonthFolderDirectory)) {
            return null;
        }

        String filePath = sameMonthFolderDirectory + File.separator + fileName;
        File file = new File(filePath);
        if (file.exists()) {
            return file;
        }

        //如果不存在 可能是在上个月 文件接受完刚好下个月的情况
        String lastMonthFolderDirectory = getLastMonthFolderDirectory();
        if (ObjectUtils.isEmpty(lastMonthFolderDirectory)) {
            return null;
        }

        filePath = lastMonthFolderDirectory + File.separator + fileName;
        file = new File(filePath);
        if (file.exists()) {
            return file;
        }

        return null;
    }

    /**
     * 等待文件下载完成
     *
     * @param file       文件
     * @param fileLength 文件大小
     * @return 如果超过最大等待时间 则返回失败
     */
    private Boolean waitDownload(File file, Long fileLength) {
        if (ObjectUtils.isEmpty(file) || !file.exists() || ObjectUtils.isEmpty(fileLength)) {
            return false;
        }

        long currentTimeMillis = System.currentTimeMillis();
        do {
            if (file.length() >= fileLength) {
                return true;
            }
            SleepUtils.sleep(THREAD_SLEEP_TIME);
        } while (System.currentTimeMillis() - currentTimeMillis < MAX_WAIT_TIME);
        FileUtils.delete(file);
        return false;
    }

}
