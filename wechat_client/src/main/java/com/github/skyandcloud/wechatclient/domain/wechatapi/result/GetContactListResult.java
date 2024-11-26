package com.github.skyandcloud.wechatclient.domain.wechatapi.result;


import com.github.skyandcloud.wechatclient.domain.wechatapi.data.GetContactListDataObject;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 好友列表返回值
 * @Author zheng.jiang
 * @Date 2024/9/13 14:06
 */
@Data
public class GetContactListResult extends BaseResult<List<GetContactListDataObject>> implements Serializable, ResultInterface {


    @Override
    public Integer getSuccessCode() {
        return 0;
    }




}
