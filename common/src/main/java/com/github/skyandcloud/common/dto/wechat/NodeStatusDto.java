package com.github.skyandcloud.common.dto.wechat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description 注册dto类
 * @Author zheng.jiang
 * @Date 2024/9/4 13:00
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodeStatusDto implements Serializable {

    /**
     * 节点状态
     */
    private String status;

    /**
     * 机器人名称
     */
    private String accountName;

    /**
     * 机器人ID
     */
    private String accountId;

    /**
     * 节点权重
     */
    private Integer nodeWeight;

}
