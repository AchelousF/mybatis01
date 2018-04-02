package com.achelous.mapper;

import com.achelous.beans.MethodMapper;
import com.achelous.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

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
        //check the method is a valid mapperRegistry（get @mapper and the method relation）
        Map<String, MethodMapper> mapper = sqlSession.getMethodMapper(interfaceMapper);
        MethodMapper methodMapper = mapper.get(method.getName());
        System.out.println("SQL ------->" + methodMapper.getSql());
        System.out.println("Parameter ------->" + args[0]);
        return sqlSession.selectOne(methodMapper.getSql(), args[0], methodMapper.getReturnType());
    }
}
