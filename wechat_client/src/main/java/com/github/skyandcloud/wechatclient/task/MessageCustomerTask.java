package com.github.skyandcloud.wechatclient.task;

import com.github.skyandcloud.common.enums.MessageHandlerTypeEnums;
import com.github.skyandcloud.common.vo.MessageVo;
import com.github.skyandcloud.wechatclient.server.message.MessageReplayServer;
import com.github.skyandcloud.wechatclient.server.message.impl.MessageReplayServerImpl;
import org.apache.commons.lang3.ObjectUtils;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Description 消息消费者任务
 * @Author zheng.jiang
 * @Date 2024/11/22 15:31
 */
public class MessageCustomerTask implements Runnable {

    private final MessageReplayServer messageReplayServer = new MessageReplayServerImpl();

    private final LinkedBlockingDeque<MessageVo> messageDeque;

    public MessageCustomerTask(LinkedBlockingDeque<MessageVo> messageDeque) {
        this.messageDeque = messageDeque;
    }

    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        while (true) {
            try {
                MessageVo message = messageDeque.take();
                MessageHandlerTypeEnums messageHandlerType = message.getMessageHandlerTypeEnums();
                if (ObjectUtils.isEmpty(messageHandlerType) || !MessageHandlerTypeEnums.REPLIED.equals(messageHandlerType)) {
                    continue;
                }

                messageReplayServer.handle(message);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
