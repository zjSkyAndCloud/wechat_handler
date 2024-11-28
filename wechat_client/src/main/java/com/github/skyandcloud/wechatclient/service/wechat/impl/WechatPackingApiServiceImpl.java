package com.github.skyandcloud.wechatclient.service.wechat.impl;

import com.github.skyandcloud.wechatclient.domain.wechatapi.data.UserInfoDataObject;
import com.github.skyandcloud.wechatclient.domain.wechatapi.param.HookSyncMsgParam;
import com.github.skyandcloud.wechatclient.domain.wechatapi.param.SendAtTextParam;
import com.github.skyandcloud.wechatclient.domain.wechatapi.param.SendTextMsgParam;
import com.github.skyandcloud.wechatclient.domain.wechatapi.result.*;
import com.github.skyandcloud.wechatclient.service.wechat.WechatBaseApiService;
import com.github.skyandcloud.wechatclient.service.wechat.WechatPackingApiService;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description
 * @Author zheng.jiang
 * @Date 2024/9/1 16:31
 */
@Slf4j
public class WechatPackingApiServiceImpl implements WechatPackingApiService {

    private static final WechatBaseApiService BASE_API_SERVER = new WechatBaseApiServiceImpl();

    private static final String TIME_OUT = "10000";

    @Override
    public Boolean isLogin() {
        CheckLoginResult result = BASE_API_SERVER.checkLogin();
        return result.isSuccess();
    }

    @Override
    public Boolean syncHookMessage() {
        HookSyncMsgParam hookSyncMsgParam = new HookSyncMsgParam();
        hookSyncMsgParam.setEnableHttp(0);
        hookSyncMsgParam.setTimeout(TIME_OUT);
        HookSyncMsgResult result = BASE_API_SERVER.hookSyncMsg(hookSyncMsgParam);
        return result.isSuccess();
    }

    @Override
    public void clickEnterWeChat() throws Exception {
        ClickEnterWeChatResult clickEnterWeChatResult = BASE_API_SERVER.clickEnterWeChat();
        if (clickEnterWeChatResult.isSuccess()) {
            throw new Exception("weChat.exe is not running");
        }
    }

    @Override
    public UserInfoDataObject getUserInfo() throws Exception {
        UserInfoResult userInfoResult = BASE_API_SERVER.userInfo();
        if (!userInfoResult.isSuccess()) {
            throw new Exception("获取用户信息失败！");
        }

        return userInfoResult.getData();
    }

    @Override
    public Boolean sendTextMessage(String wxid, String message) {
        SendTextMsgParam sendTextMsgParam = new SendTextMsgParam();
        sendTextMsgParam.setWxid(wxid);
        sendTextMsgParam.setMsg(message);
        SendTextMsgResult result = BASE_API_SERVER.sendTextMsg(sendTextMsgParam);
        return result.isSuccess();
    }

    @Override
    public Boolean sendTextAtMessage(String chatGroupId, String atUser, String message) {
        SendAtTextParam sendAtTextParam = new SendAtTextParam();
        sendAtTextParam.setChatRoomId(chatGroupId);
        sendAtTextParam.setWxids(atUser);
        sendAtTextParam.setMsg(message);
        SendAtTextResult result = BASE_API_SERVER.sendAtText(sendAtTextParam);
        return result.isSuccess();
    }

}
