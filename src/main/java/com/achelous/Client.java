package com.achelous;

import com.achelous.beans.Test;
import com.achelous.configuration.Configuration;
import com.achelous.configuration.DataSource;
import com.achelous.configuration.MapperRegistry;
import com.achelous.configuration.executor.SimpleExecutor;
import com.achelous.dao.TestMapper;
import com.achelous.session.impl.DefaultSqlSession;

/**
 * @Auther: fanJiang
 * @Date: Create in 21:45 2018/4/2
 */
public class Client {

    public static void main(String[] args) {
        DataSource dataSource = getDataSource();

        Configuration configuration = new Configuration(dataSource, new MapperRegistry());
        DefaultSqlSession sqlSession = new DefaultSqlSession(configuration, new SimpleExecutor(configuration));
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        Test test = mapper.selectByPrimaryKey(1);
        System.out.println(test);
    }

    private static DataSource getDataSource(){
        DataSource dataSource = new DataSource();
        dataSource.setDriver("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/gp");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }
}
