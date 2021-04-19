package com.testvagrant.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OptimusIo {

    public static void main(String[] args) {
        SpringApplication.run(OptimusIo.class,args);
    }

}
