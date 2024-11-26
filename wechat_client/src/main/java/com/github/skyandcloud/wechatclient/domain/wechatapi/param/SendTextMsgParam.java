package com.github.skyandcloud.wechatclient.domain.wechatapi.param;

import lombok.Data;

/**
 * @Description 发送文本消息入参
 * @Author zheng.jiang
 * @Date 2024/9/14 14:37
 */
@Data
public class SendTextMsgParam {

    /**
     * 接收人wxid
     */
    private String wxid;

    /**
     * 消息文本内容
     */
    private String msg;

}
