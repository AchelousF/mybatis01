package com.achelous.jdbc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: FanJiang.
 * @date: Created by ody on 13:44 2018/4/2.
 */
public class MapperProxy implements InvocationHandler {


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // TODO  check the method is a valid mapperRegistry（get @mapper and the method relation）
        return null;
    }
}
