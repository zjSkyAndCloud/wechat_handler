package com.github.skyandcloud.wechatserver.netty.handler;

import com.github.skyandcloud.common.constant.NodeStatusConstant;
import com.github.skyandcloud.common.dto.wechat.NodeStatusDto;
import com.github.skyandcloud.wechatserver.service.ClientNodeDispatchCenterServer;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description 服务器接受节点状态消息handler
 * @Author zheng.jiang
 * @Date 2024/11/28 15:25
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class ServerStatusHandler extends SimpleChannelInboundHandler<NodeStatusDto> {

    @Autowired
    private ClientNodeDispatchCenterServer clientNodeDispatchCenterServer;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, NodeStatusDto nodeStatusDto) throws Exception {
        if (ObjectUtils.isEmpty(nodeStatusDto)) {
            log.error("status message is empty");
            return;
        }

        String nodeStatus = nodeStatusDto.getStatus();
        if (ObjectUtils.isEmpty(nodeStatus)) {
            log.error("node status is empty");
            return;
        }

        final Channel channel = channelHandlerContext.channel();

        if (NodeStatusConstant.ONLINE.equals(nodeStatus)) {
            clientNodeDispatchCenterServer.nodeRegister(channel, nodeStatusDto);
        }

        if (NodeStatusConstant.OFFLINE.equals(nodeStatus)) {
            clientNodeDispatchCenterServer.nodeUnregister(channel);
        }

        if (NodeStatusConstant.ALIVE.equals(nodeStatus)) {
            clientNodeDispatchCenterServer.nodeHeartbeatDetection(channel);
        }
    }
}
