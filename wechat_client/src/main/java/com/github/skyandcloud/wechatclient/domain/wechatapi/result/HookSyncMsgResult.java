package com.github.skyandcloud.wechatclient.domain.wechatapi.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description hook消息返回值
 * @Author zheng.jiang
 * @Date 2024/9/13 14:04
 */
@Data
public class HookSyncMsgResult extends BaseResult<HookSyncMsgResult> implements Serializable, ResultInterface {

    @Override
    public Integer getSuccessCode() {
        return 0;
    }
}
