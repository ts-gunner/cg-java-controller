package com.forty.cg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.forty.cg.mapper")
public class CgJavaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CgJavaApiApplication.class, args);
    }

}
