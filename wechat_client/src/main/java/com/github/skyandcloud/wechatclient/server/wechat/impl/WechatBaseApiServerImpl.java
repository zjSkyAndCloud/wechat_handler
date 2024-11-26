package com.github.skyandcloud.wechatclient.server.wechat.impl;

import com.github.skyandcloud.common.utils.HttpUtils;
import com.github.skyandcloud.wechatclient.domain.wechatapi.param.*;
import com.github.skyandcloud.wechatclient.domain.wechatapi.result.*;
import com.github.skyandcloud.wechatclient.server.wechat.WechatBaseApiServer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import static com.github.skyandcloud.wechatclient.constant.ApiUrlConstant.*;

/**
 * @Description
 * @Author zheng.jiang
 * @Date 2024/9/13 14:55
 */
@Slf4j
public class WechatBaseApiServerImpl implements WechatBaseApiServer {

    /**
     * 注入的服务地址
     */
    private static String API_PATH = "http://127.0.0.1:19088/api/";

    @Override
    public CheckLoginResult checkLogin() {
        return post(CHECK_LOGIN_URL, CheckLoginResult.class);
    }

    @Override
    public UserInfoResult userInfo() {
        return post(USER_INFO_URL, UserInfoResult.class);
    }

    @Override
    public SendTextMsgResult sendTextMsg(SendTextMsgParam sendTextMsgParam) {
        return post(SEND_TEXT_MSG_URL, sendTextMsgParam, SendTextMsgResult.class);
    }

    @Override
    public HookSyncMsgResult hookSyncMsg(HookSyncMsgParam hookSyncMsgParam) {
        return post(HOOK_SYNC_MSG_URL, hookSyncMsgParam, HookSyncMsgResult.class);
    }

    @Override
    public UnhookSyncMsgResult unhookSyncMsg() {
        return post(UN_HOOK_SYNC_MSG_URL, UnhookSyncMsgResult.class);
    }

    @Override
    public GetContactListResult getContactList() {
        return post(GET_CONTACT_LIST_URL, GetContactListResult.class);
    }

    @Override
    public GetDBInfoResult getDBInfo() {
        return post(GET_DB_INFO_URL, GetDBInfoResult.class);
    }

    @Override
    public ExecSqlResult execSql(ExecSqlParam execSqlParam) {
        return post(EXEC_SQL_URL, execSqlParam, ExecSqlResult.class);
    }

    @Override
    public LockWeChatResult lockWeChat() {
        return post(LOCK_WECHAT_URL, LockWeChatResult.class);
    }

    @Override
    public UnlockWeChatResult unlockWechat() {
        return post(UNLOCK_WECHAT_URL, UnlockWeChatResult.class);
    }

    @Override
    public ClickEnterWeChatResult clickEnterWeChat() {
        return post(CLICK_ENTER_WECHAT_URL, ClickEnterWeChatResult.class);
    }

    @Override
    public ForwardMsgResult forwardMsg(ForwardMsgParam forwardMsgParam) {
        return post(FORWARD_MSG_URL, forwardMsgParam, ForwardMsgResult.class);
    }

    @Override
    public SendImagesMsgResult sendImagesMsg(SendImagesMsgParam sendImagesMsgParam) {
        return post(SEND_IMAGES_MSG_URL, sendImagesMsgParam, SendImagesMsgResult.class);
    }

    @Override
    public SendFileMsgResult sendFileMsg(SendFileMsgParam sendFileMsgParam) {
        return post(SEND_FILE_MSG_URL, sendFileMsgParam, SendFileMsgResult.class);
    }

    @Override
    public SendAtTextResult sendAtText(SendAtTextParam sendAtTextParam) {
        return post(SEND_AT_TEXT_URL, sendAtTextParam, SendAtTextResult.class);
    }

    @Override
    public SendMultiAtTextResult sendMultiAtText(SendMultiAtTextParam sendMultiAtTextParam) {
        return post(SEND_MULTI_AT_TEXT_URL, sendMultiAtTextParam, SendMultiAtTextResult.class);
    }

    @Override
    public GetLoginUrlResult getLoginUrl() {
        return post(GET_LOGIN_URL_URL, GetLoginUrlResult.class);
    }

    @Override
    public TranslateVoiceResult translateVoice(TranslateVoiceParam translateVoiceParam) {
        return post(TRANSLATE_VOICE_URL, translateVoiceParam, TranslateVoiceResult.class);
    }

    @Override
    public GetTranslateVoiceTextResult getTranslateVoiceText(GetTranslateVoiceTextParam getTranslateVoiceTextParam) {
        return post(GET_TRANSLATE_VOICE_TEXT_URL, getTranslateVoiceTextParam, GetTranslateVoiceTextResult.class);

    }

    @Override
    public OpenUrlByWeChatResult openUrlByWeChat(OpenUrlByWeChatParam openUrlByWeChatParam) {
        return post(OPEN_URL_BY_WE_CHAT_URL, openUrlByWeChatParam, OpenUrlByWeChatResult.class);
    }

    @SneakyThrows
    public <T extends BaseResult> T post(String url, Class<T> tClass) {
        return HttpUtils.post(API_PATH + url, tClass);
    }

    @SneakyThrows
    public <T extends BaseResult> T post(String url, Object params, Class<T> tClass) {
        return HttpUtils.post(API_PATH + url, params, tClass);
    }

}
