package com.github.skyandcloud.wechatclient.service.start.impl;

import com.github.skyandcloud.wechatclient.config.PropertiesConfig;
import com.github.skyandcloud.wechatclient.service.start.RunService;
import com.github.skyandcloud.wechatclient.task.ClientNettyTask;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @Description 客户端Netty服务 与服务器交互
 * @Author zheng.jiang
 * @Date 2024/9/2 13:22
 */
@Slf4j
public class ClientNettyServiceImpl implements RunService {


    private static ClientNettyTask nettyClientTask;

    /**
     * 默认的节点权重
     */
    private final static String DEFAULT_NODE_WEIGHT = "5";

    /**
     * client启动
     */
    public void run() throws Exception {
        log.info("netty client start");

        String serverIp = PropertiesConfig.getProperties(PropertiesConfig.SERVER_IP_KEY);
        String serverPortValue = PropertiesConfig.getProperties(PropertiesConfig.SERVER_PORT_KEY);
        if (ObjectUtils.isEmpty(serverIp) || ObjectUtils.isEmpty(serverPortValue)) {
            throw new Exception("server ip or port is empty");
        }

        Integer serverPort = Integer.parseInt(serverPortValue);
        String nodeWeightValue = PropertiesConfig.getProperties(PropertiesConfig.NODE_WEIGHT);
        if (ObjectUtils.isEmpty(nodeWeightValue)) {
            nodeWeightValue = DEFAULT_NODE_WEIGHT;
        }

        Integer nodeWeight = Integer.parseInt(nodeWeightValue);
        nettyClientTask = new ClientNettyTask(nodeWeight, serverIp, serverPort);
        new Thread(nettyClientTask).start();
    }

    /**
     * 发送消息到服务器
     *
     * @param msg
     * @throws Exception
     */
    public static void sendMessage(Object msg) throws Exception {
        nettyClientTask.sendMessage(msg);
    }


}
