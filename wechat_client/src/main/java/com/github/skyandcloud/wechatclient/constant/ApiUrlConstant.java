package com.github.skyandcloud.wechatclient.constant;

/**
 * @Description 接口路径
 * @Author zheng.jiang
 * @Date 2024/9/1 16:49
 * @see "https://github.com/ttttupup/wxhelper/blob/dev-3.9.10.19/doc/3.9.8.25.md"
 */
public class ApiUrlConstant {

    /**
     * hook消息
     */
    public static final String HOOK_SYNC_MSG_URL = "hookSyncMsg";

    /**
     * 取消HOOK消息
     */
    public static final String UN_HOOK_SYNC_MSG_URL = "unhookSyncMsg";

    /**
     * 检查是否登录
     */
    public static final String CHECK_LOGIN_URL = "checkLogin";

    /**
     * 获取用户信息
     */
    public static final String USER_INFO_URL = "userInfo";

    /**
     * 发送文本消息
     */
    public static final String SEND_TEXT_MSG_URL = "sendTextMsg";

    /**
     * 好友列表
     */
    public static final String GET_CONTACT_LIST_URL = "getContactList";

    /**
     * 获取数据库信息
     */
    public static final String GET_DB_INFO_URL = "getDBInfo";

    /**
     * 查询数据库
     */
    public static final String EXEC_SQL_URL = "execSql";

    /**
     * 锁定微信
     */
    public static final String LOCK_WECHAT_URL = "lockWeChat";

    /**
     * 解锁微信
     */
    public static final String UNLOCK_WECHAT_URL = "unlockWeChat";

    /**
     * 打开微信时的进入微信按钮
     */
    public static final String CLICK_ENTER_WECHAT_URL = "clickEnterWeChat";

    /**
     * 转发微信消息
     */
    public static final String FORWARD_MSG_URL = "forwardMsg";

    /**
     * 发送图片
     */
    public static final String SEND_IMAGES_MSG_URL = "sendImagesMsg";

    /**
     * 发送文件消息
     */
    public static final String SEND_FILE_MSG_URL = "sendFileMsg";

    /**
     * 发送@消息
     */
    public static final String SEND_AT_TEXT_URL = "sendAtText";

    /**
     * 发送多个不同@消息
     */
    public static final String SEND_MULTI_AT_TEXT_URL = "sendMultiAtText";

    /**
     * 获取扫码登录地址
     */
    public static final String GET_LOGIN_URL_URL = "getLoginUrl";

    /**
     * 语音消息转换文本
     */
    public static final String TRANSLATE_VOICE_URL = "translateVoice";

    /**
     * 获取语音转文本结果
     */
    public static final String GET_TRANSLATE_VOICE_TEXT_URL = "getTranslateVoiceText";

    /**
     * 通过浏览器打开url
     */
    public static final String OPEN_URL_BY_WE_CHAT_URL = "openUrlByWeChat";




}
