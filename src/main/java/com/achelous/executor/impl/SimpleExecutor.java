package com.achelous.executor.impl;

import com.achelous.configuration.Configuration;
import com.achelous.configuration.DataSource;
import com.achelous.executor.Executor;
import com.achelous.handler.StatementHandler;

import java.sql.Connection;
import java.sql.DriverManager;

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
    public <T> T query(String statement, Object param, Class type) {
        Connection connection = getConnection();
        return new StatementHandler().query(connection, statement, param, type);
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



}
