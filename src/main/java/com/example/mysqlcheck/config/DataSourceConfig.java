package com.example.mysqlcheck.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;

/**
 * @author : WuMingrui
 * @date : 2022/8/11 14:22
 */
@Configuration
public class DataSourceConfig {
    @Bean(name = "showDataSource")
    @ConfigurationProperties("spring.datasource.druid.show")
    public DataSource showDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "testDataSource")
    @ConfigurationProperties("spring.datasource.druid.test")
    public DataSource testDataSource(){
        return DruidDataSourceBuilder.create().build();
    }


}
