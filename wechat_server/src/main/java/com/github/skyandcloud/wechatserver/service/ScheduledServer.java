package com.github.skyandcloud.wechatserver.service;

/**
 * @Description 定时任务接口
 * @Author zheng.jiang
 * @Date 2024/11/28 17:27
 */
public interface ScheduledServer {

    /**
     * 开始执行节点心跳任务
     */
    void startNodeHeartbeatScheduled();


}
