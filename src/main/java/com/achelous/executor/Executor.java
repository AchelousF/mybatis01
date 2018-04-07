package com.achelous.executor;

/**
 * @Author: FanJiang.
 * @date: Created by ody on 13:40 2018/4/2.
 */
public interface Executor {

    <T> T query(String statement, Object param, Class type);

    int insert(String statement, Object param);
}
