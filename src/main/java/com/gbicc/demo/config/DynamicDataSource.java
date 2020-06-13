package com.gbicc.demo.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


import javax.sql.DataSource;
import java.util.Map;

/**\
 * 定义一个动态数据源：
 * 继承AbstractRoutingDataSource 抽象类，并重写determineCurrentLookupKey（）方法
 */
public class DynamicDataSource extends AbstractRoutingDataSource  {

    @Override
    protected Object determineCurrentLookupKey() {
        DataBaseType dataBaseType = DataSourceType.getDataBaseType();
        return dataBaseType;
    }
}
