package com.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

/**
 * spring boot 启动类
 * spring boot采用内嵌式服务器，通过main方法启动web项目！
 * 内嵌 tomcat和jetty两种服务器！
 * @SrpingBoootApplication 开启spring boot注解扫描、配置扫描等功能
 */
@SpringBootApplication
public class BookManageApplication {

    /**
     * 启动服务器main方法
     * @param args
     */
    public static void main(String[] args) {

        //项目启动类 包含一些默认的参数配置
        SpringApplication.run(BookManageApplication.class,args);
    }
}
