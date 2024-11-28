package com.github.skyandcloud.wechatclient;

import com.github.skyandcloud.wechatclient.config.HandlerLoader;
import com.github.skyandcloud.wechatclient.service.start.RunService;
import com.github.skyandcloud.wechatclient.service.start.impl.InjectorServiceImpl;
import com.github.skyandcloud.wechatclient.service.start.impl.WechatNettyServiceImpl;
import com.github.skyandcloud.wechatclient.service.start.impl.WechatServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description 启动方法
 * @Author zheng.jiang
 * @Date 2024/11/21 16:28
 */
@Slf4j
public class WechatClient {

    public static void main(String[] args) throws Exception {
        //读取配置
        HandlerLoader.init();
        //dll注入
        RunService injector = new InjectorServiceImpl();
        injector.run();
        //微信开启消息发送服务
        RunService wechatServer = new WechatServiceImpl();
        wechatServer.run();
        //开启本地接受消息服务
        RunService wechatNettyServer = new WechatNettyServiceImpl();
        wechatNettyServer.run();
//        //开启与服务端交互程序
//        RunServer clientNettyServer = new ClientNettyServerImpl();
//        clientNettyServer.run();
    }
}
