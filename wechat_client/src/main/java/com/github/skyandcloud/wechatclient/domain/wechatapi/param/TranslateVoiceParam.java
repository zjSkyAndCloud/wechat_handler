package com.github.skyandcloud.wechatclient.domain.wechatapi.param;

import lombok.Data;

/**
 * @Description 语音消息转换文本入参
 * @Author zheng.jiang
 * @Date 2024/9/13 14:50
 */
@Data
public class TranslateVoiceParam {

    /**
     * 消息id
     */
    private String msgId;

}
