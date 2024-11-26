package com.github.skyandcloud.wechatclient.domain.wechatapi.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description 取消hook消息返回值
 * @Author zheng.jiang
 * @Date 2024/9/13 14:05
 */
@Data
public class UnhookSyncMsgResult extends BaseResult<UnhookSyncMsgResult> implements Serializable, ResultInterface {


    @Override
    public Integer getSuccessCode() {
        return 0;
    }
}
