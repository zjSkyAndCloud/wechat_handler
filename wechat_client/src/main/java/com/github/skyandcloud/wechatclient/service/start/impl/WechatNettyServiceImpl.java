package com.github.skyandcloud.wechatclient.service.start.impl;

import com.github.skyandcloud.wechatclient.config.PropertiesConfig;
import com.github.skyandcloud.wechatclient.service.start.RunService;
import com.github.skyandcloud.wechatclient.task.WechatNettyTask;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description 用于接收微信消息
 * @Author zheng.jiang
 * @Date 2024/9/4 14:55
 */
@Slf4j
public class WechatNettyServiceImpl implements RunService {

    public void run() throws Exception {
        String wechatServerIp = PropertiesConfig.getProperties(PropertiesConfig.WECHAT_SERVER_IP_KEY);
        String wechatServerPort = PropertiesConfig.getProperties(PropertiesConfig.WECHAT_SERVER_PORT_KEY);
        WechatNettyTask hookNettyServerTask = new WechatNettyTask(wechatServerIp, wechatServerPort);
        new Thread(hookNettyServerTask).start();
        log.info("hook server start");
    }

}
