package com.gbicc.demo.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DynamicDataSourceAspect {
    @Before("@annotation(dataSource)")//拦截我们的注解
    public void changeDataSource(JoinPoint point, DataSource dataSource) throws Throwable {
        DataBaseType value = dataSource.value();
        if (value.equals(DataBaseType.TEST01)){
            DataSourceType.setDataBaseType(DataBaseType.TEST01);
        }else if (value.equals(DataBaseType.TEST02)){
            DataSourceType.setDataBaseType(DataBaseType.TEST02);
        }else {
            DataSourceType.setDataBaseType(DataBaseType.TEST01);//默认使用主数据库
        }

    }

    @After("@annotation(dataSource)") //清除数据源的配置
    public void restoreDataSource(JoinPoint point, DataSource dataSource) {
        DataSourceType.clearDataBaseType();


    }
}
