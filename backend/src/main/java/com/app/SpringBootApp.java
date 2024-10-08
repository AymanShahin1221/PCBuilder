package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootApp
{
    public static void main(String[] args) throws Exception
    {
        SpringApplication.run(SpringBootApp.class, args);
    }
}
