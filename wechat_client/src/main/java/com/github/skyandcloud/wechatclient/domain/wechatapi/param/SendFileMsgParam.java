package com.github.skyandcloud.wechatclient.domain.wechatapi.param;

import lombok.Data;

/**
 * @Description 发送文件入参
 * @Author zheng.jiang
 * @Date 2024/9/13 14:35
 */
@Data
public class SendFileMsgParam {

    /**
     * 接收人
     */
    private String wxid;

    /**
     * 图片路径
     */
    private String filePath;

}
