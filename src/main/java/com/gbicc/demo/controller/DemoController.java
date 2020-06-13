package com.gbicc.demo.controller;


import com.gbicc.demo.service.DemoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoServiceImpl;

    @RequestMapping("hello")
    public void hello(){
        Integer index = demoServiceImpl.test01hello();
        System.out.println("1数据源当前用户数为："+index);
        Integer index2 = demoServiceImpl.test02hello();
        System.out.println("2数据源当前用户数为："+index2);
        Integer index3 = demoServiceImpl.hello();
        System.out.println("3数据源当前用户数为："+index3);

    }
}
