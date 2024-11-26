package com.github.skyandcloud.wechatclient.domain.wechatapi.result;

import com.github.skyandcloud.wechatclient.domain.wechatapi.data.UserInfoDataObject;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 获取用户信息返回值
 * @Author zheng.jiang
 * @Date 2024/9/12 15:54
 */
@Data
public class UserInfoResult extends BaseResult<UserInfoDataObject> implements Serializable, ResultInterface {


}
