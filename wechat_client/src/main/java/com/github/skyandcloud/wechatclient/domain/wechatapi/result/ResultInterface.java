package com.github.skyandcloud.wechatclient.domain.wechatapi.result;

import org.apache.commons.lang3.ObjectUtils;

/**
 * @Description 结果接口
 * @Author zheng.jiang
 * @Date 2024/9/14 15:03
 */
public interface ResultInterface {

    /**
     * 获取成功的编码
     *
     * @return
     */
    default Integer getSuccessCode(){
        return 1;
    }

    /**
     * 获取结果编码
     *
     * @return
     */
    Integer getCode();

    /**
     * 判断是否成功
     *
     * @return
     */
    default Boolean isSuccess() {
        if (ObjectUtils.isEmpty(getSuccessCode())) {
            return false;
        }

        if (ObjectUtils.isEmpty(getCode())) {
            return false;
        }

        return getSuccessCode().equals(getCode());
    }
}
