package com.github.skyandcloud.wechatclient.server.start.impl;

import com.github.skyandcloud.wechatclient.config.GlobalStaticConfig;
import com.github.skyandcloud.wechatclient.domain.wechatapi.data.UserInfoDataObject;
import com.github.skyandcloud.wechatclient.server.start.RunServer;
import com.github.skyandcloud.wechatclient.server.wechat.WechatPackingApiServer;
import com.github.skyandcloud.wechatclient.server.wechat.impl.WechatPackingApiServerImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description 启动微信服务 向wechat netty发送消息
 * @Author zheng.jiang
 * @Date 2024/11/26 17:19
 */
@Slf4j
public class WechatServerImpl implements RunServer {

    private static final WechatPackingApiServer wechatServer = new WechatPackingApiServerImpl();

    public void run() throws Exception {
        setAccountInfo();
        syncHookMessage();
    }

    private static void setAccountInfo() throws Exception {
        log.info("get account info");
        UserInfoDataObject userInfo = wechatServer.getUserInfo();
        GlobalStaticConfig.setCurrentDataPath(userInfo.getCurrentDataPath());
        GlobalStaticConfig.setWxid(userInfo.getWxid());
        GlobalStaticConfig.setName(userInfo.getName());
        log.info("account info read finish");
    }


    /**
     * 开启Hook信息
     * 接口文档里写的指定端口无效
     * 在resources/config.ini配置
     */
    private static void syncHookMessage() throws Exception {
        log.info("hook server start");
        Boolean success = wechatServer.syncHookMessage();
        if (!success) {
            log.error("hook server start fail");
            throw new Exception("hook server start fail");
        }
        log.info("hook server start finish");
    }
}
