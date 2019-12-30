package com.cfy.android.carrent;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cfy.android.carrent.mapper")
public class CarrentApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarrentApplication.class, args);
    }

}
