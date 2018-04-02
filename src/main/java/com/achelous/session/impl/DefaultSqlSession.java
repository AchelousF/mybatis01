package com.achelous.session.impl;

import com.achelous.jdbc.Configuration;
import com.achelous.jdbc.Executor;
import com.achelous.session.SqlSession;

/**
 * @Author: FanJiang.
 * @date: Created by ody on 10:27 2018/4/2.
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;
    private Executor executor;


    public <T> T getMapper(Class<?> clazz) {
        return null;
    }

    public <T> T selectOne(String statement, String param, Class<?> type) {
        return null;
    }
}
