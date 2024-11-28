package com.github.skyandcloud.wechatclient.service.strategic;

import com.github.skyandcloud.common.dto.wechat.ClientMessageDto;
import com.github.skyandcloud.wechatclient.domain.message.WechatMessagePackagingEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 策略模式服务接口
 * @Author zheng.jiang
 * @Date 2024/9/8 15:24
 */
@Slf4j
public class StrategicMessageContentService {

    private static Map<Integer, Class<? extends StrategicMessageHandlerService>> messageHandlerServerMap = new HashMap<>();

    /**
     * 注册消息处理类
     *
     * @param type
     * @param tClass
     */
    public static void registerStrategicMessageHandlerServer(Integer type, Class<? extends StrategicMessageHandlerService> tClass) {
        messageHandlerServerMap.put(type, tClass);
    }

    public static ClientMessageDto handler(WechatMessagePackagingEntity message) throws Exception {
        if (ObjectUtils.isEmpty(message)) {
            log.info("hookMessageResult is null");
            return null;
        }

        Integer type = message.getType();
        if (!messageHandlerServerMap.containsKey(type)) {
            log.info("message handler is null");
            return null;
        }

        Class<? extends StrategicMessageHandlerService> tClass = messageHandlerServerMap.get(type);
        StrategicMessageHandlerService handlerServer = tClass.newInstance();
        return handlerServer.handler(message);
    }


}
