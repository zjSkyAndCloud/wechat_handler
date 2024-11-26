package com.github.skyandcloud.wechatclient.server.wechat;

import com.github.skyandcloud.wechatclient.domain.wechatapi.param.*;
import com.github.skyandcloud.wechatclient.domain.wechatapi.result.*;

/**
 * @Description 提供的基础服务接口
 * @Author zheng.jiang
 * @Date 2024/9/12 15:53
 */
public interface WechatBaseApiServer {

    /**
     * 检查微信是否登录
     */
    CheckLoginResult checkLogin();

    /**
     * 获取登录用户信息
     */
    UserInfoResult userInfo();

    /**
     * 发送文本消息
     */
    SendTextMsgResult sendTextMsg(SendTextMsgParam sendTextMsgParam);

    /**
     * hook消息
     */
    HookSyncMsgResult hookSyncMsg(HookSyncMsgParam hookSyncMsgParam);

    /**
     * 取消hook消息
     */
    UnhookSyncMsgResult unhookSyncMsg();

    /**
     * 好友列表
     */
    GetContactListResult getContactList();

    /**
     * 获取数据库信息和句柄
     */
    GetDBInfoResult getDBInfo();

    /**
     * 查询数据库
     */
    ExecSqlResult execSql(ExecSqlParam execSqlParam);

    /**
     * 锁定微信
     */
    LockWeChatResult lockWeChat();

    /**
     * 解锁微信
     */
    UnlockWeChatResult unlockWechat();

    /**
     * 打开微信时的进入微信按钮
     */
    ClickEnterWeChatResult clickEnterWeChat();

    /**
     * 转发微信消息
     */
    ForwardMsgResult forwardMsg(ForwardMsgParam forwardMsgParam);

    /**
     * 发送图片
     */
    SendImagesMsgResult sendImagesMsg(SendImagesMsgParam sendImagesMsgParam);

    /**
     * 发送文件
     */
    SendFileMsgResult sendFileMsg(SendFileMsgParam sendFileMsgParam);

    /**
     * 发送@消息
     */
    SendAtTextResult sendAtText(SendAtTextParam sendAtTextParam);

    /**
     * 发送多个不同@消息
     */
    SendMultiAtTextResult sendMultiAtText(SendMultiAtTextParam sendMultiAtTextParam);

    /**
     * 未登录状态下，获取登录地址，转换成二维码，手机扫码登录
     */
    GetLoginUrlResult getLoginUrl();

    /**
     * 语音消息转换文本
     */
    TranslateVoiceResult translateVoice(TranslateVoiceParam translateVoiceParam);

    /**
     * 获取语音转文本结果 （可以使用数据库查询功能查询，该接口只是为了方便减少hook）
     */
    GetTranslateVoiceTextResult getTranslateVoiceText(GetTranslateVoiceTextParam getTranslateVoiceTextParam);

    /**
     * 微信内置浏览器打开url，或者本地浏览器打开url
     */
    OpenUrlByWeChatResult openUrlByWeChat(OpenUrlByWeChatParam openUrlByWeChatParam);

}
