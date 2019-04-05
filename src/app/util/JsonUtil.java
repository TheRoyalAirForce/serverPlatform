package app.util;

import com.alibaba.fastjson.JSON;

/**
 * @fileName JsonUtil.java
 * @package com.lzy.thesocketdemo
 * @description Json解析工具类
 */
public class JsonUtil {
 
    /**
     * 将javaBean转换成json对象
     * 
     * @param paramObject
     *            需要解析的对象
     */
    public static String createJsonString(Object paramObject) {
        String str = JSON.toJSONString(paramObject);
        return str;
    }

    /**
     * 对单个javaBean进行解析
     * 用fastjson 将json字符串解析为一个 JavaBean 
     * 调用方式示例：Person person = JsonUtil.getObject(jsonString, Person.class);
     * @param <T>
     * @param paramJson 需要解析的json字符串
     * @param paramCls 需要转换成的类
     * @return
     */
    public static <T> T getObject(String paramJson, Class<T> paramCls) {
        T t = null;
        try {
            t = JSON.parseObject(paramJson, paramCls);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

}
