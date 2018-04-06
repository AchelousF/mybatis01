package com.achelous.handler;

import com.achelous.mapper.ParamMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
        PreparedStatement pstm;
        try {
            Map<Integer, ParamMapper> paramMapperMap = (Map<Integer, ParamMapper>) param;
            for (Map.Entry<Integer, ParamMapper> entry: paramMapperMap.entrySet()) {
                if (paramMapperMap.size() > 1) {
                    statement = statement.replace("#{" + entry.getValue().getName() + "}", entry.getValue().getValue());
                } else {
                    statement = statement.replaceAll("#\\{.+}", entry.getValue().getValue());
                }
            }
            pstm = connection.prepareStatement(statement);
            return resultSetHandler.query(pstm, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
