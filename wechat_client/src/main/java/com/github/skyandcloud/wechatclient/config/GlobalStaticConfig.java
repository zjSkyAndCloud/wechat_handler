package com.github.skyandcloud.wechatclient.config;

/**
 * @Description 全局的静态变量存放
 * @Author zheng.jiang
 * @Date 2024/9/15 13:20
 */
public class GlobalStaticConfig {

    /**
     * 当前数据目录,登录的账号目录
     */
    private static String currentDataPath;

    /**
     * 昵称
     */
    private static String name;

    /**
     * wxid
     */
    private static String wxid;

    public static String getCurrentDataPath() {
        return currentDataPath;
    }

    public static void setCurrentDataPath(String currentDataPath) {
        GlobalStaticConfig.currentDataPath = currentDataPath;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        GlobalStaticConfig.name = name;
    }

    public static String getWxid() {
        return wxid;
    }

    public static void setWxid(String wxid) {
        GlobalStaticConfig.wxid = wxid;
    }
}
