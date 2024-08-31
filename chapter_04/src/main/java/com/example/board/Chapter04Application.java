package com.example.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.board.repository")
public class Chapter04Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter04Application.class, args);
    }

}
