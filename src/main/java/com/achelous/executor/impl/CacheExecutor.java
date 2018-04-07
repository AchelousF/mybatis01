package com.achelous.executor.impl;

import com.achelous.executor.Executor;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: fanJiang
 * @Date: Create in 10:43 2018/4/6
 */
public class CacheExecutor implements Executor{

    private static final Map<String, Object> cacheMap = new HashMap<>();

    private Executor delegate;

    public CacheExecutor(Executor delegate) {
        this.delegate = delegate;
    }

    @Override
    public <T> T query(String statement, Object param, Class type) {
        String cacheKey = getCacheKey(type, statement, param);
        Object cache = cacheMap.get(cacheKey);
        if (cache == null) {
            cacheMap.put(cacheKey, null);
            cache = delegate.query(statement, param, type);
            cacheMap.put(cacheKey, cache);
            return (T) cache;
        }
        return (T) cache;
    }

    @Override
    public int insert(String statement, Object param) {
        return delegate.insert(statement, param);
    }

    private String getCacheKey(Class type, String statement, Object param) {
        return type + "-" + statement + "-" + param;
    }
}
