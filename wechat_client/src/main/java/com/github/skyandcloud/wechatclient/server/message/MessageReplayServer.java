package com.github.skyandcloud.wechatclient.server.message;

import com.github.skyandcloud.common.vo.MessageVo;

/**
 * @Description 消息处理接口
 * @Author zheng.jiang
 * @Date 2024/11/22 15:32
 */
public interface MessageReplayServer {

    /**
     * 消息处理
     *
     * @param messageVo
     * @return
     */
    Boolean handle(MessageVo messageVo);

    /**
     * 回复组群消息
     *
     * @param messageVo 消息结果
     */
    Boolean replayGroupMessage(MessageVo messageVo);

    /**
     * 回复组群@消息
     *
     * @param messageVo 消息结果
     */
    Boolean replayGroupAtMessage(MessageVo messageVo);

    /**
     * 回复个人消息
     *
     * @param messageVo 消息结果
     */
    Boolean replayUserMessage(MessageVo messageVo);

    /**
     * 忽略消息
     *
     * @param messageVo
     */
    Boolean ignoreMessage(MessageVo messageVo);


}
