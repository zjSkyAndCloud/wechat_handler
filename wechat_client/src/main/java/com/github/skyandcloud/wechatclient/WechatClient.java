package com.github.skyandcloud.wechatclient;

import com.github.skyandcloud.wechatclient.config.HandlerLoader;
import com.github.skyandcloud.wechatclient.server.start.RunServer;
import com.github.skyandcloud.wechatclient.server.start.impl.ClientNettyServerImpl;
import com.github.skyandcloud.wechatclient.server.start.impl.InjectorServerImpl;
import com.github.skyandcloud.wechatclient.server.start.impl.WechatNettyServerImpl;
import com.github.skyandcloud.wechatclient.server.start.impl.WechatServerImpl;
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
        RunServer injector = new InjectorServerImpl();
        injector.run();
        //微信开启消息发送服务
        RunServer wechatServer = new WechatServerImpl();
        wechatServer.run();
        //开启本地接受消息服务
        RunServer wechatNettyServer = new WechatNettyServerImpl();
        wechatNettyServer.run();
//        //开启与服务端交互程序
//        RunServer clientNettyServer = new ClientNettyServerImpl();
//        clientNettyServer.run();
    }
}
