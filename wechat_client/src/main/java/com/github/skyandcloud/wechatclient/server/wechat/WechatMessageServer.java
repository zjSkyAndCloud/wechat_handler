package com.github.skyandcloud.wechatclient.server.wechat;

import com.github.skyandcloud.wechatclient.domain.message.WechatMessageEntity;
import com.github.skyandcloud.wechatclient.domain.message.WechatMessagePackagingEntity;

/**
 * @Description 微信消息处理接口
 * @Author zheng.jiang
 * @Date 2024/11/25 15:29
 */
public interface WechatMessageServer {

    /**
     * 包装微信消息
     *
     * @param message 初始微信消息
     * @return
     */
    WechatMessagePackagingEntity packingMessage(WechatMessageEntity message);

}
