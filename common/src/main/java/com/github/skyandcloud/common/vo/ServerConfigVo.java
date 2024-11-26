package com.github.skyandcloud.common.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description 服务端配置Vo类
 * @Author zheng.jiang
 * @Date 2024/11/26 17:13
 */
@Data
@NoArgsConstructor
public class ServerConfigVo implements Serializable {

    /**
     * 是否设置延迟
     */
    private Boolean isDelay;

    /**
     * 最小延迟时间
     */
    private Long minDelay;

    /**
     * 设置最大延迟时间
     */
    private Long maxDelay;

}
