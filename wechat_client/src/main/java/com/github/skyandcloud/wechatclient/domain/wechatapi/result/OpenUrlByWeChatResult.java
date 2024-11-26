package com.github.skyandcloud.wechatclient.domain.wechatapi.result;

import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

import java.io.Serializable;

/**
 * @Description 通过浏览器打开url返回值
 * @Author zheng.jiang
 * @Date 2024/9/13 14:53
 */
@Data
public class OpenUrlByWeChatResult extends BaseResult<OpenUrlByWeChatResult> implements Serializable, ResultInterface {

    @Override
    public Boolean isSuccess() {
        if (ObjectUtils.isEmpty(getCode())) {
            return false;
        }

        return getCode() > 0;
    }

}
