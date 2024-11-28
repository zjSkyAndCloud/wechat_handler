package com.github.skyandcloud.wechatserver.service;

import com.github.skyandcloud.common.dto.wechat.NodeStatusDto;
import com.github.skyandcloud.common.vo.MessageVo;
import com.github.skyandcloud.common.vo.ServerConfigVo;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;

import java.util.Map;

/**
 * @Description 节点调度中心接口
 * @Author zheng.jiang
 * @Date 2024/11/28 15:32
 */
public interface ClientNodeDispatchCenterServer {

    /**
     * 节点注册
     *
     * @param channel
     * @param nodeStatusDto
     */
    void nodeRegister(Channel channel, NodeStatusDto nodeStatusDto);

    /**
     * 节点注销
     *
     * @param channel
     */
    void nodeUnregister(Channel channel);

    void nodeUnregister(ChannelId channelId);

    /**
     * 节点心跳检测
     *
     * @param channel
     */
    void nodeHeartbeatDetection(Channel channel);

    /**
     * 获取上次心跳map
     * @return
     */
    Map<ChannelId,Long> getLastHeartbeatMap();

    /**
     * 群发服务器配置变更信息
     *
     * @param serverConfig
     */
    void sendServerConfig(ServerConfigVo serverConfig);

    /**
     * 私发送服务器配置信息
     *
     * @param serverConfig
     */
    void sendServerConfig(ChannelId channelId, ServerConfigVo serverConfig);

}
