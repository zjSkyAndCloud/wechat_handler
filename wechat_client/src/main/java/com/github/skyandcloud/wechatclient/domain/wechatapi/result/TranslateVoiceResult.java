package com.github.skyandcloud.wechatclient.domain.wechatapi.result;

import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

import java.io.Serializable;

/**
 * @Description 语音转文本返回值
 * @Author zheng.jiang
 * @Date 2024/9/13 14:49
 */
@Data
public class TranslateVoiceResult extends BaseResult<TranslateVoiceResult> implements Serializable, ResultInterface {

    @Override
    public Boolean isSuccess() {
        if (ObjectUtils.isEmpty(getCode())) {
            return false;
        }

        return getCode() > 0;
    }


}
