package com.achelous.session.impl;

import com.achelous.configuration.Configuration;
import com.achelous.executor.Executor;
import com.achelous.mapper.MapperProxy;
import com.achelous.session.SqlSession;

import java.lang.reflect.Proxy;

/**
 * @Author: FanJiang.
 * @date: Created in 10:27 2018/4/2.
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;
    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    public <T> T getMapper(Class<?> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new MapperProxy(this, clazz));
    }

    public <T> T selectOne(String statement, Object param, Class<?> type) {
        return executor.query(statement, param, type);
    }

    public <T> T getMethodMapper(Class<?> clazz) {
        return configuration.getMapper(clazz);
    }

}
