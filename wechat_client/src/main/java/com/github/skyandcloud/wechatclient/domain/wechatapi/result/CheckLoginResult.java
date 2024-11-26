package com.github.skyandcloud.wechatclient.domain.wechatapi.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description 获取用户信息返回值
 * @Author zheng.jiang
 * @Date 2024/9/12 15:54
 */
@Data
public class CheckLoginResult extends BaseResult<CheckLoginResult> implements Serializable, ResultInterface {
}
