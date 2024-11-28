package com.github.skyandcloud.wechatclient.netty.handler;

import com.github.skyandcloud.common.vo.ServerConfigVo;
import com.github.skyandcloud.wechatclient.config.ServerGlobalStaticConfig;
import com.github.skyandcloud.wechatclient.service.message.MessageHandlerService;
import com.github.skyandcloud.wechatclient.service.message.impl.MessageHandlerServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @Description 接受服务器注册配置消息
 * @Author zheng.jiang
 * @Date 2024/11/26 16:25
 */

@Slf4j
public class AcceptServerRegisterConfigHandler extends SimpleChannelInboundHandler<ServerConfigVo> {

    private final MessageHandlerService messageHandlerService = new MessageHandlerServiceImpl();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ServerConfigVo messageVo) throws Exception {
        if (ObjectUtils.isNotEmpty(messageVo.getIsDelay())) {
            log.info("set delay :{}", messageVo.getIsDelay());
            ServerGlobalStaticConfig.setIsDelay(messageVo.getIsDelay());
        }

        if (ObjectUtils.isNotEmpty(messageVo.getMinDelay())) {
            log.info("set minDelay :{}", messageVo.getMinDelay());
            ServerGlobalStaticConfig.setMinDelay(messageVo.getMinDelay());
        }

        if (ObjectUtils.isNotEmpty(messageVo.getMaxDelay())) {
            log.info("set maxDelay :{}", messageVo.getMaxDelay());
            ServerGlobalStaticConfig.setMaxDelay(messageVo.getMaxDelay());
        }
    }

}
