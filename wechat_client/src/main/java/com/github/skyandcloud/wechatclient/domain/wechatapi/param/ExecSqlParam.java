package com.github.skyandcloud.wechatclient.domain.wechatapi.param;

import lombok.Data;

/**
 * @Description 查询数据库入参
 * @Author zheng.jiang
 * @Date 2024/9/13 14:27
 */
@Data
public class ExecSqlParam {

    /**
     * 数据库句柄
     */
    private Integer dbHandle;

    /**
     * 执行的sql
     */
    private String sql;

}
