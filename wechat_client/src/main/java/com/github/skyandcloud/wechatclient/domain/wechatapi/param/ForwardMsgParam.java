package com.github.skyandcloud.wechatclient.domain.wechatapi.param;

import lombok.Data;

/**
 * @Description 转发微信消息入参
 * @Author zheng.jiang
 * @Date 2024/9/13 14:33
 */
@Data
public class ForwardMsgParam {

    /**
     * 接收人id
     */
    private String wxid;

    /**
     * 消息id
     */
    private String msgId;

}
