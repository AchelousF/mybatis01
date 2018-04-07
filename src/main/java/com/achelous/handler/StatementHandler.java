package com.achelous.handler;

import com.achelous.mapper.ParamMapper;
import com.achelous.typeHandler.StringTypeHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

/**
 * @Auther: fanJiang
 * @Date: Create in 21:01 2018/4/3
 */
public class StatementHandler {

    private ResultSetHandler resultSetHandler;

    public StatementHandler() {
        resultSetHandler = new ResultSetHandler();
    }

    public <T> T query(Connection connection, String statement, Object param, Class type) {
        PreparedStatement pstm = null;
        try {
            Map<Integer, ParamMapper> paramMapperMap = (Map<Integer, ParamMapper>) param;
            for (Map.Entry<Integer, ParamMapper> entry: paramMapperMap.entrySet()) {
                if (paramMapperMap.size() > 1) {
                    statement = statement.replace("#{" + entry.getValue().getName() + "}", handleParam(entry.getValue().getValue()));
                } else {
                    statement = statement.replaceAll("#\\{.+}", handleParam(entry.getValue().getValue()));
                }
            }
            pstm = connection.prepareStatement(statement);
            return resultSetHandler.query(pstm, type);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStatement(pstm);
        }
        return null;
    }

    public int insert(Connection connection, String statement, Object param) {
        PreparedStatement pstm = null;
        try {
            Map<Integer, ParamMapper> paramMapperMap = (Map<Integer, ParamMapper>) param;
            for (Map.Entry<Integer, ParamMapper> entry: paramMapperMap.entrySet()) {
                if (paramMapperMap.size() > 1) {
                    statement = statement.replace("#{" + entry.getValue().getName() + "}", handleParam(entry.getValue().getValue()));
                } else {
                    statement = statement.replaceAll("#\\{.+}", handleParam(entry.getValue().getValue()));
                }
            }
            pstm = connection.prepareStatement(statement);
            return resultSetHandler.insert(pstm);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStatement(pstm);
        }
        return 0;
    }


    private String handleParam(Object name) {
        //   这里偷了个懒  直接判断入参类型   按理应该在容器注入时就确定入参类型
        if (name instanceof String) {
            return StringTypeHandler.setValue(name.toString());
        }
        return name.toString();
    }

    private void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
