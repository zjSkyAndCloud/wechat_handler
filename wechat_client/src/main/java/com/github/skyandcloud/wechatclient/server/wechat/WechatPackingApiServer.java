package com.github.skyandcloud.wechatclient.server.wechat;

import com.github.skyandcloud.wechatclient.domain.wechatapi.data.UserInfoDataObject;

/**
 * @Description 封装后微信服务
 * @Author zheng.jiang
 * @Date 2024/8/27 15:15
 */
public interface WechatPackingApiServer {

    /**
     * 判断是否登录
     *
     * @return
     */
    Boolean isLogin();

    /**
     * 开始hook信息
     *
     * @return
     */
    Boolean syncHookMessage();

    /**
     * 尝试点击进去微信
     *
     * @return
     */
    @Deprecated
    void clickEnterWeChat() throws Exception;

    /**
     * 获取用户信息
     *
     * @return
     */
    UserInfoDataObject getUserInfo() throws Exception;


    /**
     * 发送文本信息
     *
     * @param wxid
     * @param message
     * @return
     * @throws Exception
     */
    Boolean sendTextMessage(String wxid, String message);

    /**
     * 发送文本@信息
     *
     * @param chatGroupId
     * @param atUser
     * @param message
     * @return
     * @throws Exception
     */
    Boolean sendTextAtMessage(String chatGroupId, String atUser, String message);

}
