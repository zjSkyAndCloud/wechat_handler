package com.github.skyandcloud.common.utils;

/**
 * @Description 睡眠工具类
 * @Author zheng.jiang
 * @Date 2024/11/24 15:52
 */
public class SleepUtils {

    public static void sleep(Long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
