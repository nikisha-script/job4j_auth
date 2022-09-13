package ru.job4j.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Job4jAuthApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Job4jAuthApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Job4jAuthApplication.class, args);
        System.out.println("go to: http://localhost:8189/");
    }
}
