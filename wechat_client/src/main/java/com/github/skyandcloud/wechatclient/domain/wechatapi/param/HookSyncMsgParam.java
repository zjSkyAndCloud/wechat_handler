package com.github.skyandcloud.wechatclient.domain.wechatapi.param;

import lombok.Data;

/**
 * @Description hook消息入参
 * @Author zheng.jiang
 * @Date 2024/9/14 14:13
 */
@Data
public class HookSyncMsgParam {

    /**
     * 本地服务端端口，用来接收消息内容
     */
    private String port;

    /**
     * 服务端ip地址，用来接收消息内容，可以是任意ip,即tcp客户端连接的服务端的ip
     */
    private String ip;

    /**
     * http的请求地址，enableHttp=1时，不能为空
     */
    private String url;

    /**
     * 超时时间，单位ms
     */
    private String timeout;

    /**
     * true/false ：true.启用http false.不启用http
     */
    private Integer enableHttp;

}
