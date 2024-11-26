package com.github.skyandcloud.common.enums;

import lombok.Getter;

/**
 * @Description 处理结果枚举类
 * @Author zheng.jiang
 * @Date 2024/8/14 14:55
 */
@Getter
public enum MessageHandlerTypeEnums {

    /**
     * 待回复
     */
    WAITING_FOR_REPLY("待回复", 1),

    /**
     * 忽略消息
     */
    IGNORE("忽略", 2),

    /**
     * 已处理
     */
    REPLIED("已处理", 3),

    /**
     * 无需回复
     */
    NO_REPLY("无需回复", 4),

    /**
     * 处理失败
     */
    PROCESSING_FAILED("处理失败", 5);

    MessageHandlerTypeEnums(String typeName, Integer typeValue) {
        this.typeName = typeName;
        this.typeValue = typeValue;
    }

    /**
     * 类型名称
     */
    private final String typeName;

    /**
     * 类型值
     */
    private final Integer typeValue;

}
