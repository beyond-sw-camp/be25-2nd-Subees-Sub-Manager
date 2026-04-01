package com.subees.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.subees.backend.user.mapper")
@SpringBootApplication
public class SubeesBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubeesBackApplication.class, args);
    }

}