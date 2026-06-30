package com.wjy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 集中一下图像的配置类吧
 *
 * @Author 祝英台炸油条
 * @Time : 2022/6/5 22:23
 **/
@Configuration
public class WebPicConfig implements WebMvcConfigurer {


    //TODO 这个配置类的目的是什么  就是注册了一个类似于拦截器吧  看到对应的资源 会将其修改成相应的地址
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String ASSETS_PATH = System.getProperty("user.dir");
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:" + ASSETS_PATH + "/upload/");

    }

}
