package com.jawwwad.ss_l2.config;

import com.jawwwad.ss_l2.entities.UserEntity;
import com.jawwwad.ss_l2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialDB implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) {
        if (!userRepository.existsByUserName("user"))
            userRepository.save(new UserEntity().setUserName("user").setPassword("12345"));
    }
}
