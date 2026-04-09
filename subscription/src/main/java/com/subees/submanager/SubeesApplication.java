package com.subees.submanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {
        "com.subees.submanager.user.mapper",
        "com.subees.submanager.recommend.mapper"
})
@SpringBootApplication
public class SubeesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubeesApplication.class, args);
    }

}