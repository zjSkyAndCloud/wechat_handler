package com.github.skyandcloud.wechatclient.domain.wechatapi.data;

import lombok.Data;

/**
 * @Description 获取语言转文本
 * @Author zheng.jiang
 * @Date 2024/9/15 15:54
 */
@Data
public class GetTranslateVoiceTextDataObject {

    /**
     * 转换文本
     */
    private String transtext;
}
