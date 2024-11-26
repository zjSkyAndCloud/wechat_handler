package com.github.skyandcloud.common.dto.wechat;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description 微信消息Dto类
 * @Author zheng.jiang
 * @Date 2024/9/7 14:39
 */
@Data
@NoArgsConstructor
public class ClientMessageDto implements Serializable {

    /**
     * 发送群
     */
    private String fromGroup;

    /**
     * 发送人
     */
    private String fromUser;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 文件流
     */
    private byte[] fileInputStream;

    public ClientMessageDto(String fromGroup, String fromUser, String content) {
        this.setFromGroup(fromGroup);
        this.setFromUser(fromUser);
        this.setContent(content);
    }

    public ClientMessageDto(String fromGroup, String fromUser, String fileName, byte[] fileInputStream) {
        this.setFromGroup(fromGroup);
        this.setFromUser(fromUser);
        this.setContent(fileName);
        this.setFileInputStream(fileInputStream);
    }
}
