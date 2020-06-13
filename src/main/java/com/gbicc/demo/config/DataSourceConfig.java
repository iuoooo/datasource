package com.gbicc.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackages = "com.gbicc.demo.mapper",sqlSessionFactoryRef = "SqlSessionFactory")
public class DataSourceConfig {


    //bean创建对象，交给spring管理
    @Bean(name = "test1DataSource")
    @Primary//指定那个为主数据源
    @ConfigurationProperties(prefix = "spring.datasource.test1")//绑定配置文件中的属性到bean中
    public DataSource getDateSource1(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "test2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test2")//读取的数据源前缀, 跟yml文件对应
    public DataSource getDateSource2(){
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "dynamicDataSource")
    public DynamicDataSource DataSource(@Qualifier("test1DataSource")DataSource test1datasource,
                        @Qualifier("test2DataSource")DataSource test2datasource ){
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataBaseType.TEST01,test1datasource);
        targetDataSources.put(DataBaseType.TEST02,test2datasource);
        DynamicDataSource dataSource=new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(test1datasource);
        return dataSource;
    }

    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory test1SqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource)
            throws Exception{
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/**/*Mapper.xml"));
        return bean.getObject();
    }

}
