package com.achelous;

import com.achelous.beans.Blog;
import com.achelous.beans.Test;
import com.achelous.configuration.Configuration;
import com.achelous.configuration.DataSource;
import com.achelous.configuration.MapperRegistry;
import com.achelous.dao.BlogMapper;
import com.achelous.executor.impl.CacheExecutor;
import com.achelous.executor.impl.SimpleExecutor;
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
        DefaultSqlSession sqlSession = new DefaultSqlSession(configuration, new CacheExecutor(new SimpleExecutor(configuration)));
        // TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        // Test test = mapper.selectByPrimaryKey(1);
        // Test test = mapper.selectByIdAndName(1, "2");
        // 测试一级缓存
        //test = mapper.selectByPrimaryKey(1);

        /** 多表测试 */
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = mapper.selectByPrimaryKey(5);
//        int i = mapper.insertOne(5, "mybatis02");
        System.out.println(blog);
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
