package com.achelous.jdbc;

import com.achelous.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: FanJiang.
 * @date: Created by ody on 13:44 2018/4/2.
 */
public class MapperProxy implements InvocationHandler {

    private final SqlSession sqlSession;
    private final Class<?> interfaceMapper;

    public MapperProxy(SqlSession sqlSession, Class<?> interfaceMapper) {
        this.sqlSession = sqlSession;
        this.interfaceMapper = interfaceMapper;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // TODO  check the method is a valid mapperRegistry（get @mapper and the method relation）
        Object mapper = sqlSession.getMapper(interfaceMapper);

        return sqlSession.selectOne("", args, null);
    }
}
