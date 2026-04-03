package com.subees.subscription;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.subees.subscription.user.mapper")
@SpringBootApplication
public class SubeesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubeesApplication.class, args);
    }
}
