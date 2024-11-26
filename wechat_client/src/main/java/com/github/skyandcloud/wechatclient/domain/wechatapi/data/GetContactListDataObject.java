package com.github.skyandcloud.wechatclient.domain.wechatapi.data;

import com.github.skyandcloud.wechatclient.constant.WechatConstant;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @Description 好友列表
 * @Author zheng.jiang
 * @Date 2024/9/15 15:48
 */
@Data
public class GetContactListDataObject {

    /**
     * 自定义账号
     */
    private String customAccount;

    /**
     * 昵称 加密后？
     */
    private String encryptName;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 简拼
     */
    private String pinyin;

    /**
     * 全拼
     */
    private String pinyinAll;

    /**
     * 备注
     */
    private String remark;

    /**
     * 备注拼音
     */
    private String remarkPinyin;

    /**
     * 备注全拼
     */
    private String remarkPinyinAll;

    /**
     * 标签id
     */
    private String labelIds;

    /**
     * wxid
     */
    private String wxid;

    /**
     * 未知
     */
    private Integer reserved1;


    /**
     * 未知
     */
    private Integer reserved2;

    /**
     * 未知
     */
    private Integer type;

    /**
     * 未知
     */
    private Integer verifyFlag;


    /**
     * 是否是群
     *
     * @return
     */
    public Boolean isGroup() {
        if (ObjectUtils.isEmpty(getWxid())) {
            return null;
        }

        return wxid.endsWith(WechatConstant.GROUP_REMARK);
    }

    /**
     * 判断是否是用户
     *
     * @return
     */
    public Boolean isUser() {
        return !isGroup();
    }
}
