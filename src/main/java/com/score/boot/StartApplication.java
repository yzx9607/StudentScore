package com.score.boot;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication //SpringBoot启动类注解
@EnableAutoConfiguration  //开启自动化配置
@ComponentScan("com.score") //类似于Spring的基础类扫描包，用来扫描实体，接口和控制器
@MapperScan("com.score.dao") //mybatis的接口扫描包
public class StartApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }
}
