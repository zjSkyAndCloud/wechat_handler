package com.github.skyandcloud.wechatclient.service.message.impl;

import com.github.skyandcloud.common.vo.MessageVo;
import com.github.skyandcloud.wechatclient.service.message.MessageHandlerService;
import com.github.skyandcloud.wechatclient.task.MessageCustomerTask;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Description 消息消费服务实现类
 * @Author zheng.jiang
 * @Date 2024/9/23 16:52
 */
public class MessageHandlerServiceImpl implements MessageHandlerService {

    /**
     * 一个队列 用于按顺序输出信息
     */
    private static final LinkedBlockingDeque<MessageVo> messageDeque = new LinkedBlockingDeque<>(64);

    @Override
    public void start() {
        MessageCustomerTask messageCustomerTask = new MessageCustomerTask(messageDeque);
        Thread thread = new Thread(messageCustomerTask);
        thread.start();
    }

    @Override
    public Boolean addMessage(MessageVo messageVo) {
        return messageDeque.add(messageVo);
    }

    @Override
    public Boolean removeMessage(MessageVo messageVo) {
        return messageDeque.remove(messageVo);
    }

}
