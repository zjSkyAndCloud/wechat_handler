package com.github.skyandcloud.wechatclient.domain.wechatapi.result;

import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

import java.io.Serializable;

/**
 * @Description 解锁微信返回值
 * @Author zheng.jiang
 * @Date 2024/9/13 14:29
 */
@Data
public class UnlockWeChatResult extends BaseResult<UnlockWeChatResult> implements Serializable, ResultInterface {

    @Override
    public Boolean isSuccess() {
        if (ObjectUtils.isEmpty(getCode())) {
            return false;
        }

        return getCode() > 0;
    }
}
