package com.github.skyandcloud.wechatclient.server.strategic;

import com.github.skyandcloud.common.dto.wechat.ClientMessageDto;
import com.github.skyandcloud.wechatclient.domain.message.WechatMessagePackagingEntity;

/**
 * @Description 消息处理Handler
 * @Author zheng.jiang
 * @Date 2024/9/8 14:33
 */
public interface StrategicMessageHandlerServer {


    void register();

    /**
     * 处置策略类接口
     *
     * @param message 接收到的初始消息
     * @return 客户端消息
     */
    ClientMessageDto handler(WechatMessagePackagingEntity message) throws Exception;

}
