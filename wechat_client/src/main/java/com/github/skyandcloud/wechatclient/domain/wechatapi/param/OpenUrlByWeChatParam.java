package com.github.skyandcloud.wechatclient.domain.wechatapi.param;

import lombok.Data;

/**
 * @Description 通过浏览器打开url入参
 * @Author zheng.jiang
 * @Date 2024/9/13 14:54
 */
@Data
public class OpenUrlByWeChatParam {

    /**
     * 需要打开的链接
     */
    private String url;

    /**
     * 内置或者本地浏览器mask ，后四位按位取值，具体参数意义自行尝试，简单可直接传0，1，2
     */
    private Integer flag;

}
