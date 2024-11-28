package com.github.skyandcloud.wechatserver.task;

import com.github.skyandcloud.wechatserver.service.ClientNodeDispatchCenterServer;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import org.apache.commons.lang3.ObjectUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @Description 节点心跳监测任务
 * @Author zheng.jiang
 * @Date 2024/11/28 16:48
 */
public class NodeHeartbeatDetectionTask implements Runnable {

    /**
     * 客户端定时向服务器发送心跳 如果超过额度次数没有收到消息 则认为他宕机并且移除
     */

    /**
     * 最大的监测次数
     */
    private final Integer maxWaitHeartbeatNum = 3;

    /**
     * 心跳周期
     */
    private final Long heartbeatInterval = 30 * 1000L;

    /**
     * 节点调度中心服务
     */
    private ClientNodeDispatchCenterServer clientNodeDispatchCenterServer;

    public NodeHeartbeatDetectionTask(ClientNodeDispatchCenterServer clientNodeDispatchCenterServer) {
        this.clientNodeDispatchCenterServer = clientNodeDispatchCenterServer;
    }

    @Override
    public void run() {
        Map<ChannelId, Long> lastHeartbeatTimeMap = clientNodeDispatchCenterServer.getLastHeartbeatMap();
        if (ObjectUtils.isEmpty(lastHeartbeatTimeMap) || lastHeartbeatTimeMap.isEmpty()) {
            return;
        }

        for (ChannelId channel : lastHeartbeatTimeMap.keySet()) {
            if (!lastHeartbeatTimeMap.containsKey(channel)) {
                //设为第一次
                lastHeartbeatTimeMap.put(channel, System.currentTimeMillis());
                continue;
            }

            Long lastHeartbeatTime = lastHeartbeatTimeMap.get(channel);
            Boolean isOverDeal = isOverDeal(lastHeartbeatTime);
            if (isOverDeal) {
                clientNodeDispatchCenterServer.nodeUnregister(channel);
            }
        }
        //每秒去检测上一次
    }

    /**
     * 判断节点上次心跳时间是否超期
     *
     * @param lastHeartbeatTime
     * @return
     */
    private Boolean isOverDeal(Long lastHeartbeatTime) {
        if (ObjectUtils.isEmpty(lastHeartbeatTime)) {
            return true;
        }

        return System.currentTimeMillis() - lastHeartbeatTime > heartbeatInterval * maxWaitHeartbeatNum;
    }
}
