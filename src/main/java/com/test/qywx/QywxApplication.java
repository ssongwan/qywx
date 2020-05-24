package com.test.qywx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.test.qywx.dao")
public class QywxApplication {

    public static void main(String[] args) {
        SpringApplication.run(QywxApplication.class, args);
    }

}
