package com.yunche;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.yunche")
@MapperScan("com.yunche.infra.mapper")  // 只扫描这个具体的包
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
