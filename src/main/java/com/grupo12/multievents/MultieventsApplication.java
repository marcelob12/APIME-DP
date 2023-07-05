package com.grupo12.multievents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

@SpringBootApplication
public class MultieventsApplication {


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(5, new SecureRandom());
    }

    public static void main(String[] args) {
        SpringApplication.run(MultieventsApplication.class, args);
        System.out.println("Happy Hacking :)");

    }

}
