package com.kennysohl.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoApplication {
    //This will spin up the Spring container for the application
    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

}
