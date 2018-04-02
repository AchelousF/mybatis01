package com.achelous.session;

public interface SqlSession {

    <T> T getMapper(Class<?> clazz);

    <T> T selectOne(String statement, String param, Class<?> type);
}
