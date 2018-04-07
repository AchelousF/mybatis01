package com.achelous.handler;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Auther: fanJiang
 * @Date: Create in 21:03 2018/4/3
 */
public class ResultSetHandler {


    public <T> T query(PreparedStatement pstm, Class type) {
        try (ResultSet rs = pstm.executeQuery()) {
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

    public int insert(PreparedStatement pstm) {
        try {
            return pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private String upperField(String name) {
        char[] chars = name.toCharArray();
        chars[0] -= 32;
        return String.valueOf(chars);
    }

    private void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
