package com.github.skyandcloud.wechatclient.service.strategic.impl;

import com.github.skyandcloud.common.dto.wechat.ClientMessageDto;
import com.github.skyandcloud.common.utils.ByteArrayStreamUtils;
import com.github.skyandcloud.wechatclient.constant.MessageTypeConstant;
import com.github.skyandcloud.wechatclient.domain.message.UnescapeFileEntity;
import com.github.skyandcloud.wechatclient.domain.message.WechatMessagePackagingEntity;
import com.github.skyandcloud.wechatclient.service.strategic.StrategicMessageContentService;
import com.github.skyandcloud.wechatclient.service.strategic.StrategicMessageHandlerService;
import com.github.skyandcloud.wechatclient.service.wechat.WechatFileService;
import com.github.skyandcloud.wechatclient.utils.WechatFileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.io.*;

/**
 * @Description 文件消息处理类
 * @Author zheng.jiang
 * @Date 2024/9/8 15:15
 */
@Slf4j
public class MessageContentFileHandlerServiceImpl implements StrategicMessageHandlerService {

    private static final String SPLIT = ":\\n";

    private final WechatFileService wechatFileService = new WechatFileService();

    @Override
    public void register() {
        StrategicMessageContentService.registerStrategicMessageHandlerServer(MessageTypeConstant.FILE, MessageContentFileHandlerServiceImpl.class);
    }


    @Override
    public ClientMessageDto handler(WechatMessagePackagingEntity message) throws Exception {
        log.info("accept file message");
        if (ObjectUtils.isEmpty(message)) {
            log.info("message content is null");
            return null;
        }

        String messageContent = message.getContent();
        UnescapeFileEntity unescapeFile = WechatFileUtils.getUnescapeFile(messageContent);
        if (ObjectUtils.isEmpty(unescapeFile)) {
            return null;
        }

        try (InputStream inputStream = wechatFileService.getWechatFileInputStream(unescapeFile.getFileName(), unescapeFile.getLength())) {
            log.info("fromGroup:{}, fromUser:{}, fileName:{}", message.getFromGroup(), message.getFromUser(), unescapeFile.getFileName());
            return new ClientMessageDto(message.getFromGroup(), message.getFromUser(), unescapeFile.getFileName(), ByteArrayStreamUtils.getBytes(inputStream));
        }
    }


}
