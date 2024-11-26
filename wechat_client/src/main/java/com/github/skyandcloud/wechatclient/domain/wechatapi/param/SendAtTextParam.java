package com.github.skyandcloud.wechatclient.domain.wechatapi.param;

import lombok.Data;

/**
 * @Description 发送@消息入参
 * @Author zheng.jiang
 * @Date 2024/9/13 14:41
 */
@Data
public class SendAtTextParam {

    /**
     * wxid字符串，多个用,分隔，发送所有人传值"notify@all"
     */
    private String wxids;

    /**
     * 群id
     */
    private String chatRoomId;

    /**
     * 消息内容
     */
    private String msg;
}
