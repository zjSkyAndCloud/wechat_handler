package com.github.skyandcloud.wechatclient.service.message.impl;

import com.github.skyandcloud.common.enums.MessageHandlerTypeEnums;
import com.github.skyandcloud.common.utils.SleepUtils;
import com.github.skyandcloud.common.vo.MessageVo;
import com.github.skyandcloud.wechatclient.config.ServerGlobalStaticConfig;
import com.github.skyandcloud.wechatclient.service.message.MessageReplayService;
import com.github.skyandcloud.wechatclient.service.wechat.WechatPackingApiService;
import com.github.skyandcloud.wechatclient.service.wechat.impl.WechatPackingApiServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomUtils;

/**
 * @Description
 * @Author zheng.jiang
 * @Date 2024/11/22 15:35
 */
@Slf4j
public class MessageReplayServiceImpl implements MessageReplayService {

    /**
     * 微信服务
     */
    private final WechatPackingApiService wechatPackingApiService = new WechatPackingApiServiceImpl();

    @Override
    public Boolean handle(MessageVo message) {
        if (ObjectUtils.isEmpty(message)) {
            log.error("message is null");
            return false;
        }

        MessageHandlerTypeEnums messageHandlerType = message.getMessageHandlerTypeEnums();
        if (ObjectUtils.isEmpty(messageHandlerType)) {
            log.error("message handle type is null");
            return false;
        }

        if (MessageHandlerTypeEnums.WAITING_FOR_REPLY.equals(messageHandlerType)) {
            log.error("messageHandlerType is waiting from reply");
            return false;
        }

        if (MessageHandlerTypeEnums.IGNORE.equals(messageHandlerType) || MessageHandlerTypeEnums.NO_REPLY.equals(messageHandlerType)) {
            return ignoreMessage(message);
        }

        //设置随机延迟时间 根据服务端配置来
        if (ServerGlobalStaticConfig.getIsDelay()) {
            Long delayTime = RandomUtils.nextLong(ServerGlobalStaticConfig.getMinDelay(), ServerGlobalStaticConfig.getMaxDelay());
            SleepUtils.sleep(delayTime);
        }

        //如果有群ID和用户ID 则认为是发送群@消息
        if (ObjectUtils.isNotEmpty(message.getFromGroup()) && ObjectUtils.isNotEmpty(message.getFromUser())) {
            return replayGroupAtMessage(message);
        }

        //如果只有群ID 则认为是发送群消息
        if (ObjectUtils.isNotEmpty(message.getFromGroup())) {
            return replayGroupMessage(message);
        }

        //如果只有用户ID 则认为是发送用户私聊消息
        if (ObjectUtils.isNotEmpty(message.getFromUser())) {
            return replayUserMessage(message);
        }

        //如果三者都没有 则认为消息有误
        log.error("message don't send");
        return false;
    }

    @Override
    public Boolean replayGroupMessage(MessageVo message) {
        if (ObjectUtils.isEmpty(message)) {
            log.error("message is null");
            return false;
        }

        if (ObjectUtils.isEmpty(message.getFromGroup()) || ObjectUtils.isEmpty(message.getContent())) {
            log.error("fromGroup or content is null");
            return false;
        }

        log.info("send group message. groupId : {},  content:{} ", message.getFromGroup(), message.getContent());
        return wechatPackingApiService.sendTextMessage(message.getFromGroup(), message.getContent());
    }

    @Override
    public Boolean replayGroupAtMessage(MessageVo message) {
        if (ObjectUtils.isEmpty(message)) {
            log.error("message is null");
            return false;
        }

        if (ObjectUtils.isEmpty(message.getFromGroup()) || ObjectUtils.isEmpty(message.getFromUser()) || ObjectUtils.isEmpty(message.getContent())) {
            log.error("fromGroup or fromUser or content is null");
            return false;
        }

        log.info("send group at message. groupId : {}, userId : {}, content:{} ", message.getFromGroup(), message.getFromUser(), message.getContent());
        return wechatPackingApiService.sendTextAtMessage(message.getFromGroup(), message.getFromUser(), message.getContent());
    }

    @Override
    public Boolean replayUserMessage(MessageVo message) {
        if (ObjectUtils.isEmpty(message)) {
            log.error("message is null");
            return false;
        }

        if (ObjectUtils.isEmpty(message.getFromUser()) || ObjectUtils.isEmpty(message.getContent())) {
            log.error("fromUser or content is null");
            return false;
        }

        log.info("send user message. userId : {},  content:{} ", message.getFromUser(), message.getContent());
        return wechatPackingApiService.sendTextMessage(message.getFromUser(), message.getContent());
    }

    @Override
    public Boolean ignoreMessage(MessageVo message) {
        log.info("message is ignored");
        return true;
    }
}
