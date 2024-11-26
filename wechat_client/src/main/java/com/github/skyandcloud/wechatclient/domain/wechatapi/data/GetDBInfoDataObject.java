package com.github.skyandcloud.wechatclient.domain.wechatapi.data;

import lombok.Data;

import java.util.List;

/**
 * @Description 获取数据库信息和句柄
 * @Author zheng.jiang
 * @Date 2024/9/15 15:52
 */
@Data
public class GetDBInfoDataObject {

    /**
     * 数据库名称
     */
    private String databaseName;

    /**
     * 句柄
     */
    private String handle;

    /**
     * 表信息
     */
    private List<Table> tables;

    @Data
    public static class Table {

        /**
         * 表名
         */
        private String name;

        /**
         * rootpage
         */
        private String rootpage;

        /**
         * ddl语句
         */
        private String sql;

        /**
         * 表名
         */
        private String tableName;
    }


}
