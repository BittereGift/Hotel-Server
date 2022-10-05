package com.hotel.util;

import com.alibaba.fastjson2.JSON;

/**
 * @author Bittere_Gift
 */
public class DataUtil {

    private DataUtil() {}

    /**
     * 将 Feign 传递过来的 data 转型成对应的类
     * @param data
     * @param clazz
     * @return
     * @param <T>
     */
    public static <T> T getDataFromFeign(Object data, Class<T> clazz) {
        String jsonUser = JSON.toJSONString(data);
        return JSON.parseObject(jsonUser, clazz);
    }
}
