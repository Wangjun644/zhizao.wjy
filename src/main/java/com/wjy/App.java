package com.wjy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//扫描Mapper所在包  所有Mybatis Mapper类 生成实现，并且Spring管理（IOC）
@MapperScan("com.wjy.mapper")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

}
