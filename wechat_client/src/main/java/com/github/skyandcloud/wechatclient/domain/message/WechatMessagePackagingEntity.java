package com.github.skyandcloud.wechatclient.domain.message;

import lombok.Data;

/**
 * @Description 消息实体类
 * @Author zheng.jiang
 * @Date 2024/9/15 15:10
 */
@Data
public class WechatMessagePackagingEntity {

    /**
     * 消息体
     */
    private String content;

    /**
     * 发送人
     */
    private String fromUser;

    /**
     * 发送群
     */
    private String fromGroup;

    /**
     * 消息类型 1：文本  49：文件 其他暂不处理
     */
    private Integer type;

}
