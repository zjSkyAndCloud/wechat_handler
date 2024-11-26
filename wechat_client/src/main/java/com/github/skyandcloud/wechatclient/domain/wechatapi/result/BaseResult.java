package com.github.skyandcloud.wechatclient.domain.wechatapi.result;

/**
 * @Description 基础的返回值
 * @Author zheng.jiang
 * @Date 2024/9/12 15:55
 */
public class BaseResult<T> {

    /**
     * 返回状态,1 成功, 0失败
     */
    private Integer code;

    /**
     * 成功提示
     */
    private String result;

    /**
     * 响应内容
     */
    private T data;



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
