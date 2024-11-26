package com.github.skyandcloud.wechatclient.domain.wechatapi.result;

import com.github.skyandcloud.wechatclient.domain.wechatapi.data.GetTranslateVoiceTextDataObject;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

import java.io.Serializable;

/**
 * @Description 获取语音转文本结果返回值
 * @Author zheng.jiang
 * @Date 2024/9/13 14:49
 */
@Data
public class GetTranslateVoiceTextResult extends BaseResult<GetTranslateVoiceTextDataObject> implements Serializable, ResultInterface {

    @Override
    public Boolean isSuccess() {
        if (ObjectUtils.isEmpty(getCode())) {
            return false;
        }

        return getCode() > 0;
    }
}
