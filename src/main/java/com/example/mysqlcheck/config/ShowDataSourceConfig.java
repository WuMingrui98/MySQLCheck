package com.example.mysqlcheck.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author : WuMingrui
 * @date : 2022/8/11 14:36
 */
@Configuration
@MapperScan(basePackages = {"com.example.mysqlcheck.mapper.show"}, sqlSessionTemplateRef = "showSqlSessionTemplate")
public class ShowDataSourceConfig {
    @Autowired
    @Qualifier("showDataSource")
    private DataSource showDataSource;

    @Bean(name = "showSqlSessionFactory")
    @Primary
    public SqlSessionFactory showSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(showDataSource);
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resourcePatternResolver.getResources("classpath:mapper/show/*.xml");
        factoryBean.setMapperLocations(resources);
        return factoryBean.getObject();
    }

    @Bean(name = "showTransactionManager")
    @Primary
    public DataSourceTransactionManager showTransactionManager() {
        return new DataSourceTransactionManager(showDataSource);
    }

    @Bean(name = "showSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate showSqlSessionTemplate(
            @Qualifier("showSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
