package com.github.skyandcloud.wechatclient.domain.wechatapi.data;

import lombok.Data;

/**
 * @Description 获取扫码登录地址
 * @Author zheng.jiang
 * @Date 2024/9/15 15:53
 */
@Data
public class GetLoginUrlDataObject {

    /**
     * 登录地址
     */
    private String loginUrl;
}
