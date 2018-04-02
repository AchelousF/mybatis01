package com.achelous.configuration;

/**
 * @Author: FanJiang.
 * @date: Created in 13:40 2018/4/2.
 */
public class Configuration {

    private final MapperRegistry mapperRegistry;

    private final DataSource dataSource;

    public Configuration(DataSource dataSource, MapperRegistry mapperRegistry) {
        this.dataSource = dataSource;
        this.mapperRegistry = mapperRegistry;
    }

    public <T> T getMapper(Class<?> clazz) {
        return mapperRegistry.getMapper(clazz);
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
