package com.github.skyandcloud.wechatclient.domain.wechatapi.result;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 查询数据库返回值
 * @Author zheng.jiang
 * @Date 2024/9/13 14:22
 */
@Data
public class ExecSqlResult extends BaseResult<List<String[][]>> implements Serializable, ResultInterface {

    @Override
    public Integer getSuccessCode() {
        return 0;
    }

}
