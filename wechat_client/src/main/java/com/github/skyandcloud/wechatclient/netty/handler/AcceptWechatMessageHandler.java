package com.github.skyandcloud.wechatclient.netty.handler;

import com.alibaba.fastjson.JSON;
import com.github.skyandcloud.common.dto.wechat.ClientMessageDto;
import com.github.skyandcloud.wechatclient.domain.message.WechatMessageEntity;
import com.github.skyandcloud.wechatclient.domain.message.WechatMessagePackagingEntity;
import com.github.skyandcloud.wechatclient.server.start.impl.ClientNettyServerImpl;
import com.github.skyandcloud.wechatclient.server.strategic.StrategicMessageContentServer;
import com.github.skyandcloud.wechatclient.server.wechat.WechatMessageServer;
import com.github.skyandcloud.wechatclient.server.wechat.impl.WechatMessageServerImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @Description 接受微信消息处理类
 * @Author zheng.jiang
 * @Date 2024/11/25 15:22
 */
@Slf4j
public class AcceptWechatMessageHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 微信消息处理类
     */
    private final WechatMessageServer wechatMessageServer = new WechatMessageServerImpl();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String wechatMessageString) throws Exception {
        if (ObjectUtils.isEmpty(wechatMessageString)) {
            log.error("wechat message is empty");
            return;
        }

        WechatMessageEntity wechatMessage = JSON.parseObject(wechatMessageString, WechatMessageEntity.class);
        if (ObjectUtils.isEmpty(wechatMessage)) {
            log.error("message entity is empty");
            return;
        }


        WechatMessagePackagingEntity wechatMessagePackaging = wechatMessageServer.packingMessage(wechatMessage);
        ClientMessageDto clientMessageDto = StrategicMessageContentServer.handler(wechatMessagePackaging);
        ClientNettyServerImpl.sendMessage(clientMessageDto);
    }
}
