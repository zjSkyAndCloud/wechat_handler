package com.github.skyandcloud.wechatserver.service.impl;

import com.github.skyandcloud.common.dto.wechat.NodeStatusDto;
import com.github.skyandcloud.common.vo.MessageVo;
import com.github.skyandcloud.common.vo.ServerConfigVo;
import com.github.skyandcloud.wechatserver.service.ClientNodeDispatchCenterServer;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.netty.util.internal.ObjectUtil;
import jdk.nashorn.internal.ir.ObjectNode;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description
 * @Author zheng.jiang
 * @Date 2024/11/28 15:44
 */
@Slf4j
@Component
public class ClientNodeDispatchCenterServerImpl implements ClientNodeDispatchCenterServer {

    /**
     * channel 集合
     */
    private static final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * channel对应上次心跳时间
     */
    private static final Map<ChannelId, Long> lastHeartbeatTimeMap = new HashMap<>();

    /**
     * 节点的微信名称集合
     */
    private static final Set<String> nodeAccountNameSet = new HashSet<>();

    /**
     * 节点的微信账号集合
     */
    private static final Set<String> nodeAccountIdSet = new HashSet<>();

    /**
     * 记录每个channel权重
     */
    private static final Map<ChannelId, Integer> channelWeightMap = new ConcurrentHashMap<>();

    /**
     * 记录每个channel和账号等关联关系
     */
    private static final Map<ChannelId, NodeStatusDto> channelNodeStatusMap = new ConcurrentHashMap<>();

    /**
     * channel链表 根据节点权重排序
     */
    private static final PriorityQueue<ChannelId> channelDeque = new PriorityQueue<>(new Comparator<ChannelId>() {
        @Override
        public int compare(ChannelId o1, ChannelId o2) {
            Integer weight1 = channelWeightMap.get(o1);
            Integer weight2 = channelWeightMap.get(o2);
            return weight1 - weight2;
        }
    });


    @Override
    @Synchronized
    public void nodeRegister(Channel channel, NodeStatusDto nodeStatusDto) {
        if (ObjectUtils.isEmpty(channel)) {
            log.error("channel is null");
            return;
        }
        if (ObjectUtils.isEmpty(nodeStatusDto)) {
            log.error("node status message is null");
            return;
        }

        if (ObjectUtils.isEmpty(nodeStatusDto.getAccountName()) || ObjectUtils.isEmpty(nodeStatusDto.getAccountId())) {
            log.error("account name or id is null");
            return;
        }

        if (ObjectUtils.isEmpty(nodeStatusDto.getNodeWeight())) {
            log.error("node weight is null");
            return;
        }

        channelGroup.add(channel);
        nodeAccountNameSet.add(nodeStatusDto.getAccountName());
        nodeAccountIdSet.add(nodeStatusDto.getAccountId());
        ChannelId channelId = channel.id();
        channelWeightMap.put(channelId, nodeStatusDto.getNodeWeight());
        channelNodeStatusMap.put(channelId, nodeStatusDto);
        channelDeque.offer(channelId);
        lastHeartbeatTimeMap.put(channelId, System.currentTimeMillis());
//      todo  sendServerConfig(channelId)
    }

    @Override
    @Synchronized
    public void nodeUnregister(Channel channel) {
        if (ObjectUtils.isEmpty(channel)) {
            log.error("channel is null");
            return;
        }

        ChannelId channelId = channel.id();
        nodeUnregister(channelId);
    }

    @Override
    public void nodeUnregister(ChannelId channelId) {
        if (ObjectUtils.isEmpty(channelId)) {
            log.error("channel id is null");
            return;
        }

        channelDeque.remove(channelId);
        channelWeightMap.remove(channelId);

        NodeStatusDto nodeStatusDto = channelNodeStatusMap.get(channelId);
        channelWeightMap.remove(channelId);
        lastHeartbeatTimeMap.remove(channelId);
        if (ObjectUtils.isEmpty(nodeStatusDto)) {
            log.info("client unregister");
            return;
        }

        nodeAccountNameSet.remove(nodeStatusDto.getAccountName());
        nodeAccountIdSet.remove(nodeStatusDto.getAccountId());
        log.info("accountName:{} ,client unregister", nodeStatusDto.getAccountName());
    }

    @Override
    public void nodeHeartbeatDetection(Channel channel) {
        if (ObjectUtils.isEmpty(channel)) {
            log.error("channel is null");
            return;
        }

        ChannelId channelId = channel.id();
        NodeStatusDto nodeStatusDto = channelNodeStatusMap.get(channelId);
        lastHeartbeatTimeMap.put(channelId, System.currentTimeMillis());
        log.info("accountName:{} is alive", nodeStatusDto.getAccountName());
    }

    @Override
    public Map<ChannelId, Long> getLastHeartbeatMap() {
        return lastHeartbeatTimeMap;
    }

    @Override
    public void sendServerConfig(ChannelId channelId, ServerConfigVo serverConfig) {
        if (channelGroup.isEmpty()) {
            log.info("channelGroup is empty");
            return;
        }
        Channel channel = channelGroup.find(channelId);
        channel.writeAndFlush(serverConfig);
    }

    @Override
    public void sendServerConfig(ServerConfigVo serverConfig) {
        if (channelGroup.isEmpty()) {
            log.info("channelGroup is empty");
            return;
        }

        channelGroup.writeAndFlush(serverConfig);
        log.info("send server config to channel group");
    }
}
