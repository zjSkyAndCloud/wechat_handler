package com.github.skyandcloud.wechatserver.service;

import com.github.skyandcloud.wechatserver.task.NodeHeartbeatDetectionTask;
import com.github.skyandcloud.wechatserver.task.ServerMessageTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author zheng.jiang
 * @Date 2024/11/28 17:28
 */
@Slf4j
@Component
public class ScheduledServerImpl implements ScheduledServer {

    @Autowired
    private ClientNodeDispatchCenterServer clientNodeDispatchCenterServer;

    @Override
    public void startNodeHeartbeatScheduled() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        long initialDelay = 0;
        long period = 10;
        NodeHeartbeatDetectionTask nodeHeartbeatDetectionTask = new NodeHeartbeatDetectionTask(clientNodeDispatchCenterServer);
        scheduler.scheduleAtFixedRate(nodeHeartbeatDetectionTask, initialDelay, period, TimeUnit.SECONDS);
        log.info("node heart beat detection started");
    }
}
