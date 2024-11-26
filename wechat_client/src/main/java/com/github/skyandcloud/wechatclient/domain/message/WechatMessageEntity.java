package com.github.skyandcloud.wechatclient.domain.message;

import lombok.Data;

/**
 * @Description 消息实体类
 * @Author zheng.jiang
 * @Date 2024/9/15 15:10
 */
@Data
public class WechatMessageEntity {

    /**
     * 消息体
     */
    private String content;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 展示的不完整内容 当数据过长隐藏时显示
     */
    private String displayFullContent;

    /**
     * 发送人/群
     */
    private String fromUser;

    /**
     * 消息ID
     */
    private Long msgId;

    /**
     * 消息顺序
     */
    private Long msgSequence;

    /**
     * pid
     */
    private Long pid;

    /**
     * 加签
     */
    private String signature;

    /**
     * 接收人 均为当前账户
     */
    private String toUser;

    /**
     * 消息类型 1：文本  49：文件 其他暂不处理
     */
    private Integer type;

}
