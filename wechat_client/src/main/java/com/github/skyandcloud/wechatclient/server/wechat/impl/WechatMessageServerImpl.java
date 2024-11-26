package com.github.skyandcloud.wechatclient.server.wechat.impl;

import com.github.skyandcloud.wechatclient.domain.message.WechatMessageEntity;
import com.github.skyandcloud.wechatclient.domain.message.WechatMessagePackagingEntity;
import com.github.skyandcloud.wechatclient.server.wechat.WechatMessageServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @Description
 * @Author zheng.jiang
 * @Date 2024/11/25 15:30
 */
@Slf4j
public class WechatMessageServerImpl implements WechatMessageServer {

    private static final String SPLIT = ":\\n";

    public Boolean validate(WechatMessageEntity message) {
        if (ObjectUtils.isEmpty(message)) {
            log.error("message is null");
            return false;
        }

        if (ObjectUtils.isEmpty(message.getFromUser())) {
            log.error("from user is null");
            return false;
        }

        if (ObjectUtils.isEmpty(message.getType())) {
            log.error("message type is null");
            return false;
        }

        if (ObjectUtils.isEmpty(message.getContent())) {
            log.error("content is null");
            return false;
        }

        return true;
    }

    @Override
    public WechatMessagePackagingEntity packingMessage(WechatMessageEntity message) {
        Boolean validate = validate(message);
        if (!validate) {
            return null;
        }

        WechatMessagePackagingEntity result = new WechatMessagePackagingEntity();
        result.setType(message.getType());
        String content = message.getContent();
        //不包含分隔符则表示是私聊信息
        if (!content.contains(SPLIT)) {
            result.setFromUser(message.getFromUser());
            result.setContent(content);
            return result;
        }

        String fromUser = content.substring(0, content.indexOf(SPLIT));
        String newContent = content.substring(content.indexOf(SPLIT) + SPLIT.length());
        result.setFromGroup(result.getFromUser());
        result.setFromUser(fromUser);
        result.setContent(newContent);
        return result;
    }

}
