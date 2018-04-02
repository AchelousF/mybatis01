package com.achelous.configuration.executor;

/**
 * @Author: FanJiang.
 * @date: Created by ody on 13:40 2018/4/2.
 */
public interface Executor {

    <T> T doQuery(String statement, Object param, Class type);
}
