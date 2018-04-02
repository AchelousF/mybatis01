package com.achelous.beans;

/**
 * @Auther: fanJiang
 * @Date: Create in 22:25 2018/4/2
 */
public class MethodMapper {

    private Class returnType;
    private String sql;

    public MethodMapper(Class returnType, String sql) {
        this.returnType = returnType;
        this.sql = sql;
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
