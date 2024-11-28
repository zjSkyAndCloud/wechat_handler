package com.github.skyandcloud.wechatclient.service.strategic.impl;

import com.github.skyandcloud.common.dto.wechat.ClientMessageDto;
import com.github.skyandcloud.wechatclient.constant.MessageTypeConstant;
import com.github.skyandcloud.wechatclient.domain.message.WechatMessagePackagingEntity;
import com.github.skyandcloud.wechatclient.service.strategic.StrategicMessageContentService;
import com.github.skyandcloud.wechatclient.service.strategic.StrategicMessageHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @Description
 * @Author zheng.jiang
 * @Date 2024/9/8 14:37
 */
@Slf4j
public class MessageContentTextHandlerServiceImpl implements StrategicMessageHandlerService {

    @Override
    public void register() {
        StrategicMessageContentService.registerStrategicMessageHandlerServer(MessageTypeConstant.TEXT, MessageContentTextHandlerServiceImpl.class);
    }


    @Override
    public ClientMessageDto handler(WechatMessagePackagingEntity message) {
        log.info("accept text message");
        if (ObjectUtils.isEmpty(message)) {
            log.info("message is null");
            return null;
        }

        log.info("fromGroup:{},fromUser:{} ,content:{}", message.getFromGroup(), message.getFromUser(), message.getContent());
        return new ClientMessageDto(message.getFromGroup(), message.getFromUser(), message.getContent());
    }
}
