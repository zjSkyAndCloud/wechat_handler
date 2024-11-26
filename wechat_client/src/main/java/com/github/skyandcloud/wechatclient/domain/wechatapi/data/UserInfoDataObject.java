package com.github.skyandcloud.wechatclient.domain.wechatapi.data;

import lombok.Data;

/**
 * @Description 用户信息
 * @Author zheng.jiang
 * @Date 2024/9/15 15:55
 */
@Data
public class UserInfoDataObject {

    /**
     * 账号
     */
    private String account;

    /**
     * 头像
     */
    private String headImage;

    /**
     * 城市
     */
    private String city;

    /**
     * 省
     */
    private String province;

    /**
     * 国家
     */
    private String country;

    /**
     * 当前数据目录,登录的账号目录
     */
    private String currentDataPath;

    /**
     * 微信保存目录
     */
    private String dataSavePath;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 昵称
     */
    private String name;

    /**
     * wxid
     */
    private String wxid;

    /**
     * 个人签名
     */
    private String signature;

    /**
     * 数据库的SQLCipher的加密key，可以使用该key配合decrypt.py解密数据库
     */
    private String dbKey;
}
