package com.achelous.mapper;

import com.achelous.Enum.StatementType;
import com.achelous.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
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
        Map<Integer, ParamMapper> params = paramMapperResolve(methodMapper.getParams(), args);
        System.out.println("SQL       ------->" + methodMapper.getSql());
        System.out.println("Parameter ------->" + arrayToString(args));
        if (methodMapper.getStatementType().equals(StatementType.SELECT)) {
            return sqlSession.selectOne(methodMapper.getSql(), params, methodMapper.getReturnType());
        }
        if (methodMapper.getStatementType().equals(StatementType.INSERT)) {
            return sqlSession.insert(methodMapper.getSql(), params);
        }
        return null;
    }

    private Map<Integer, ParamMapper> paramMapperResolve(Map<Integer, String> paramMap, Object[] args) {
        Map<Integer, ParamMapper> resolveResult = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            ParamMapper param = new ParamMapper();
            param.setId(i);
            param.setName(paramMap.get(i));
            param.setValue(args[i].toString());
            resolveResult.put(i, param);
        }
        return resolveResult;
    }

    private String arrayToString(Object[] args) {
        StringBuilder param = new StringBuilder("[");
        for (int i = 0; i < args.length; i++) {
            if (i == args.length -1) {
                param.append(args[i].toString());
            } else {
                param.append(args[i].toString()).append(",");
            }
        }
        param.append("]");
        return param.toString();
    }
}
