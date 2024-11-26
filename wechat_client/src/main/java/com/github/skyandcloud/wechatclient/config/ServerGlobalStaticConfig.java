package com.github.skyandcloud.wechatclient.config;

/**
 * @Description 服务器配置的全局变量
 * @Author zheng.jiang
 * @Date 2024/11/26 17:00
 */
public class ServerGlobalStaticConfig {

    /**
     * 是否设置延迟
     */
    private static Boolean IS_DELAY = true;

    /**
     * 最小延迟时间
     */
    private static Long MIN_DELAY = 3 * 1000L;

    /**
     * 设置最大延迟时间
     */
    private static Long MAX_DELAY = 10 * 1000L;

    public static Boolean getIsDelay() {
        return IS_DELAY;
    }

    public static void setIsDelay(Boolean isDelay) {
        IS_DELAY = isDelay;
    }

    public static Long getMinDelay() {
        return MIN_DELAY;
    }

    public static void setMinDelay(Long minDelay) {
        MIN_DELAY = minDelay;
    }

    public static Long getMaxDelay() {
        return MAX_DELAY;
    }

    public static void setMaxDelay(Long maxDelay) {
        MAX_DELAY = maxDelay;
    }
}
