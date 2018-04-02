package com.achelous.configuration.executor;

import com.achelous.beans.Test;
import com.achelous.configuration.Configuration;
import com.achelous.configuration.DataSource;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;

/**
 * @Auther: fanJiang
 * @Date: Create in 21:32 2018/4/2
 */
public class SimpleExecutor implements Executor {

    private Configuration configuration;

    public SimpleExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T doQuery(String statement, Object param, Class type) {
        Connection connection = getConnection();
        PreparedStatement pstm;
        try {
            pstm = connection.prepareStatement(String.format(statement, Integer.parseInt(String.valueOf(param))));
            ResultSet rs = pstm.executeQuery();
            Object instance = type.newInstance();
            while (rs.next()) {
                Field[] fields = type.getDeclaredFields();
                for (Field field: fields) {
                    String fieldName = upperField(field.getName());
                    field.setAccessible(true);
                    if (Integer.class == field.getType()) {
                        type.getMethod("set" + fieldName, Integer.class).invoke(instance, rs.getInt(fieldName));
                    }
                    if (String.class == field.getType()) {
                        type.getMethod("set" + fieldName, String.class).invoke(instance, rs.getString(fieldName));
                    }
                }
            }
            return (T) instance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private Connection getConnection(){
        DataSource dataSource = configuration.getDataSource();
        Connection connection = null;
        try {
            Class.forName(dataSource.getDriver());
            connection = DriverManager.getConnection(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    private String upperField(String name) {
        char[] chars = name.toCharArray();
        chars[0] -= 32;
        return String.valueOf(chars);
    }

}
