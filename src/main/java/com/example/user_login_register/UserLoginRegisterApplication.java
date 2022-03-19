package com.example.user_login_register;

import com.example.user_login_register.Model.User;
import com.example.user_login_register.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserLoginRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserLoginRegisterApplication.class, args);
    }


    @Bean
    public CommandLineRunner demo(UserService userService) {

        return (args) -> {
            userService.registerUser(new User(null,"mahdi","mahdi@gmail.com","1234","ADMIN"));
            userService.registerUser(new User(null,"ghaith","ghaith@gmail.com","1234","USER"));

        };
    }
}
