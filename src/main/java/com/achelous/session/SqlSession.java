package com.achelous.session;

public interface SqlSession {

    <T> T getMapper(Class<?> clazz);

    <T> T selectOne(String statement, Object param, Class<?> type);

    <T> T getMethodMapper(Class<?> clazz);
}
