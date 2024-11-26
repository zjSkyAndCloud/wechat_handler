package com.github.skyandcloud.wechatclient.domain.message;

import lombok.Data;

/**
 * @Description 解析后文件实体类
 * @Author zheng.jiang
 * @Date 2024/11/22 16:27
 */
@Data
public class UnescapeFileEntity {

    /**
     * 文件长度
     */
    private Long length;

    /**
     * 文件名称
     */
    private String fileName;

}
