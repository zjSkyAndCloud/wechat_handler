package com.github.skyandcloud.wechatclient.netty.handler;

import com.github.skyandcloud.common.vo.MessageVo;
import com.github.skyandcloud.wechatclient.server.message.MessageHandlerServer;
import com.github.skyandcloud.wechatclient.server.message.impl.MessageHandlerServerImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @Description 接受服务器消息处理类
 * @Author zheng.jiang
 * @Date 2024/11/26 16:25
 */

@Slf4j
public class AcceptServerMessageHandler extends SimpleChannelInboundHandler<MessageVo> {

    private final MessageHandlerServer messageHandlerServer = new MessageHandlerServerImpl();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageVo messageVo) throws Exception {
        if (!validate(messageVo)) {
            return;
        }

        log.info("accept reply message fromGroup:{}, fromUser:{}, message:{}", messageVo.getFromGroup(), messageVo.getFromUser(), messageVo.getContent());
        messageHandlerServer.addMessage(messageVo);
    }

    /**
     * 参数校验
     *
     * @param messageVo
     * @return
     */
    private Boolean validate(MessageVo messageVo) {
        if (ObjectUtils.isEmpty(messageVo)) {
            log.error("message is null");
            return false;
        }

        if (ObjectUtils.isEmpty(messageVo.getMessageHandlerTypeEnums())) {
            log.error("message type is null");
            return false;
        }

        if (ObjectUtils.isEmpty(messageVo.getFromUser()) && ObjectUtils.isEmpty(messageVo.getFromGroup())) {
            log.error("from user or from group is null");
            return false;
        }

        return true;
    }
}
