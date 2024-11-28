package com.github.skyandcloud.wechatserver.netty;

import com.github.skyandcloud.wechatserver.netty.handler.ServerMessageHandler;
import com.github.skyandcloud.wechatserver.netty.handler.ServerStatusHandler;
import com.github.skyandcloud.wechatserver.service.ScheduledServer;
import com.github.skyandcloud.wechatserver.task.ServerMessageTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledExecutorService;

/**
 * @Description 服务器netty服务
 * @Author zheng.jiang
 * @Date 2024/11/27 19:21
 */

@Slf4j
@Component
public class ServerNettyInitService implements InitializingBean {

    @Value("${netty.server.port}")
    private Integer nettyServerPort;

    /**
     * 处置消息handler
     */
    @Autowired
    private ServerMessageHandler serverMessageHandler;

    /**
     * 处置状态消息handler
     */
    @Autowired
    private ServerStatusHandler serverStatusHandler;

    @Autowired
    private ScheduledServer scheduledServer;

    @Override
    public void afterPropertiesSet() throws Exception {
        ServerMessageTask serverMessageTask = new ServerMessageTask(nettyServerPort, serverMessageHandler, serverStatusHandler);
        new Thread(serverMessageTask).start();
        scheduledServer.startNodeHeartbeatScheduled();
    }
}
