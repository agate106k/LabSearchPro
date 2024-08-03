package com.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.todo.repository.jpa")
public class Todo {
    public static void main(String[] args) {
        SpringApplication.run(Todo.class, args);
    }
}