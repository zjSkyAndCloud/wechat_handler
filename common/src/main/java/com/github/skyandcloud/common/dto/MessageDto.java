package com.github.skyandcloud.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description 用于feign传输信息Dto
 * @Author zheng.jiang
 * @Date 2024/8/19 15:47
 */
@Data
public class MessageDto implements Serializable {

    /**
     * 文本消息
     */
    private String message;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件流
     */
    private byte[] fileInputStream;

}
