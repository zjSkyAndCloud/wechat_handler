package com.github.skyandcloud.wechatserver.netty.handler;

import com.github.skyandcloud.common.dto.MessageDto;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description 服务器消息处理handler
 * @Author zheng.jiang
 * @Date 2024/11/28 15:23
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class ServerMessageHandler extends SimpleChannelInboundHandler<MessageDto> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageDto messageDto) throws Exception {

    }
}
