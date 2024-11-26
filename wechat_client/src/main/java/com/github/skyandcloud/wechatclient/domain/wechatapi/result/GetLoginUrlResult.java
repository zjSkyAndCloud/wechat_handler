package com.github.skyandcloud.wechatclient.domain.wechatapi.result;

import com.github.skyandcloud.wechatclient.domain.wechatapi.data.GetLoginUrlDataObject;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

import java.io.Serializable;

/**
 * @Description 获取扫码登录地址返回值
 * @Author zheng.jiang
 * @Date 2024/9/13 14:47
 */
@Data
public class GetLoginUrlResult extends BaseResult<GetLoginUrlDataObject>  implements Serializable, ResultInterface{



    @Override
    public Boolean isSuccess() {
        if (ObjectUtils.isEmpty(getCode())) {
            return false;
        }

        return getCode() > 0;
    }

}
