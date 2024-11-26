package com.github.skyandcloud.common.vo;

import com.github.skyandcloud.common.enums.MessageHandlerTypeEnums;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description 消息Vo类
 * @Author zheng.jiang
 * @Date 2024/9/7 14:39
 */
@Data
@NoArgsConstructor
public class MessageVo implements Serializable {

    /**
     * 处理结果类型
     */
    private MessageHandlerTypeEnums messageHandlerTypeEnums;

    /**
     * 处理结果内容
     */
    private String content;

    /**
     * 接收群
     */
    private String fromGroup;

    /**
     * 接收人
     */
    private String fromUser;

    public MessageVo(MessageHandlerTypeEnums messageHandlerTypeEnums) {
        MessageVo messageResult = new MessageVo();
        messageResult.setMessageHandlerTypeEnums(messageHandlerTypeEnums);
    }
}
