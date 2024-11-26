package com.github.skyandcloud.wechatclient.domain.wechatapi.result;

import com.github.skyandcloud.wechatclient.domain.wechatapi.data.GetDBInfoDataObject;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 获取数据库信息和句柄返回值
 * @Author zheng.jiang
 * @Date 2024/9/13 14:18
 */
@Data
public class GetDBInfoResult extends BaseResult<GetDBInfoDataObject> implements Serializable, ResultInterface {


    @Override
    public Integer getSuccessCode() {
        return 0;
    }
}
