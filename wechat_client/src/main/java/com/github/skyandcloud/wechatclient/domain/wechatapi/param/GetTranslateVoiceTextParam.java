package com.github.skyandcloud.wechatclient.domain.wechatapi.param;

import lombok.Data;

/**
 * @Description 获取语音转文本结果入参
 * @Author zheng.jiang
 * @Date 2024/9/13 14:50
 */
@Data
public class GetTranslateVoiceTextParam {

    /**
     * 消息id
     */
    private String msgId;

}
