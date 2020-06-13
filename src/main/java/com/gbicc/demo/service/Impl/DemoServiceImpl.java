package com.gbicc.demo.service.Impl;

import com.gbicc.demo.config.DataBaseType;
import com.gbicc.demo.config.DataSource;
import com.gbicc.demo.config.DataSourceType;
import com.gbicc.demo.mapper.DemoMapper;
import com.gbicc.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoMapper demoMapper;


    public Integer hello(){
        //该句为手动切换数据源
        return demoMapper.hello();
    }

    @DataSource(value = DataBaseType.TEST02)
    public Integer test02hello(){
        return demoMapper.hello();
    }
    @DataSource(value = DataBaseType.TEST01)
    public Integer test01hello(){
        return demoMapper.hello();
    }
}
