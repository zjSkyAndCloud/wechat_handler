package com.github.skyandcloud.wechatclient.service.message;

import com.github.skyandcloud.common.vo.MessageVo;

/**
 * @Description 消息回复处理服务类
 * @Author zheng.jiang
 * @Date 2024/11/22 15:21
 */
public interface MessageHandlerService {

    /**
     * 启动消息消费
     */
    void start();

    /**
     * 插入信息
     *
     * @param messageVo 消息结果
     * @return 是否成功
     */
    Boolean addMessage(MessageVo messageVo);

    /**
     * 消息处理完移除信息
     *
     * @param messageVo 消息结果
     * @return 是否成功
     */
    Boolean removeMessage(MessageVo messageVo);


}
