package com.github.skyandcloud.wechatclient.config;

import com.github.skyandcloud.wechatclient.WechatClient;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Description 参数配置类
 * @Author zheng.jiang
 * @Date 2024/9/5 14:38
 */
@Slf4j
public class PropertiesConfig {

    /**
     * 配置文件名
     */
    private final static String PROPERTIES_FILE_NAME = "application.properties";

    /**
     * 服务IP
     */
    public final static String SERVER_IP_KEY = "serverIp";

    /**
     * 服务端口
     */
    public final static String SERVER_PORT_KEY = "serverPort";

    /**
     * 微信wechat服务IP
     */
    public final static String WECHAT_SERVER_IP_KEY = "wechatServerIp";

    /**
     * 微信wechat服务端口
     */
    public final static String WECHAT_SERVER_PORT_KEY = "wechatServerPort";

    /**
     * 节点权重
     */
    public final static String NODE_WEIGHT = "nodeWeight";


    private final static Properties PROPERTIES = new Properties();


    static {
        try (InputStream inputStream = WechatClient.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME)) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            log.error("load properties error", e);
            throw new RuntimeException(e);
        }
    }

    public static String getProperties(String key) {
        if (PROPERTIES.containsKey(key)) {
            return PROPERTIES.getProperty(key);
        }

        log.error("{} is not exists", key);
        return null;
    }

}
