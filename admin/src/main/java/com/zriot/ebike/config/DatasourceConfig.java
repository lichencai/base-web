package com.zriot.ebike.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.zriot.ebike.datasource.DynamicDataSource;
import com.zriot.ebike.datasource.DynamicDataSourceTransactionManager;

@Configuration
public class DatasourceConfig {

    @Primary
    @Bean(name = "writeDataSource")
    @ConfigurationProperties("spring.datasource.write")
    public DruidDataSource writeDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "readDataSource")
    @ConfigurationProperties("spring.datasource.read")
    public DruidDataSource readDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setWriteDataSource(writeDataSource());
        dynamicDataSource.setReadDataSource(readDataSource());
        return dynamicDataSource;
    }

    @Bean(name = "dynamicTransactionManager")
    public DataSourceTransactionManager dynamicTransactionManager(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) {
        return new DynamicDataSourceTransactionManager(dynamicDataSource);
    }

    @Bean
    @ConfigurationProperties(prefix = MybatisProperties.MYBATIS_PREFIX)
    public SqlSessionFactory dynamicSqlSessionFactory(
        @Qualifier("dynamicDataSource") DataSource dynamicDataSource,
        MybatisProperties properties) throws Exception{
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dynamicDataSource);
        sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(properties.getConfigLocation()));
        sessionFactory.setMapperLocations(properties.resolveMapperLocations());
        try {
            return sessionFactory.getObject();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
