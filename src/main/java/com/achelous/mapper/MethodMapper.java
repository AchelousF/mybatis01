package com.achelous.mapper;

import java.util.Map;

/**
 * @Auther: fanJiang
 * @Date: Create in 22:25 2018/4/2
 */
public class MethodMapper {

    private Class returnType;
    private String sql;
    private Map<Integer, String> params;


    public MethodMapper(Class returnType, String sql, Map<Integer, String> params) {
        this.returnType = returnType;
        this.sql = sql;
        this.params = params;
    }

    public Map<Integer, String> getParams() {
        return params;
    }

    public void setParams(Map<Integer, String> params) {
        this.params = params;
    }

    public Class getReturnType() {
        return returnType;
    }

    public void setReturnType(Class returnType) {
        this.returnType = returnType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
