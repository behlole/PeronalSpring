package com.example.spingrest.Models;


import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("user")
@Configuration
public class User {
    private String id;
    private String username;
    private Integer age;
    private String role;

    private String password;

    @Bean
    public User User() {
        return new User();
    }
}

