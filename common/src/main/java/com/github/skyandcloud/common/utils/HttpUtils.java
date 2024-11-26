package com.github.skyandcloud.common.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.ObjectUtils;

import java.io.IOException;

/**
 * @Description http工具类
 * @Author zheng.jiang
 * @Date 2024/9/4 15:59
 */
@Slf4j
public class HttpUtils {

    static OkHttpClient client = new OkHttpClient();

    /**
     * 以json post请求
     *
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    static String postJson(String url, String json) throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        if (ObjectUtils.isEmpty(json)) {
            json = "{}";
        }

        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder().url(url).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


    /**
     * 以json 发送post请求
     *
     * @param url
     * @param param
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T post(String url, Object param, Class<T> clazz) throws IOException {
        String params = JSONObject.toJSONString(param);
        log.info("post request url:{}, params:{}", url, params);
        String result = postJson(url, params);
        log.info("post response result:{}", result);
        return JSONObject.parseObject(result, clazz);
    }

    public static <T> T post(String url, Class<T> clazz) throws IOException {
        log.info("post request url:{}, ", url);
        String result = postJson(url, null);
        log.info("post response result:{}", result);
        return JSONObject.parseObject(result, clazz);
    }

}
